package spp.data.repository.implementation;

import spp.data.repository.SchoolPeriodDAO;
import spp.domain.dto.SchoolPeriodDTO;
import spp.data.exception.DataAccessException;
import spp.data.connection.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation for managing academic periods.
 * Handles complete CRUD operations for the school term configurations.
 */
public class SchoolPeriodDAOImplementation implements SchoolPeriodDAO {

    /**
     * Registers a new school period in the system.
     *
     * @param period DTO containing start and end dates.
     * @return true if successful.
     * @throws DataAccessException If SQL execution fails.
     */
    @Override
    public boolean save(SchoolPeriodDTO period) throws DataAccessException {
        String query = "INSERT INTO periodo_escolar (fecha_inicio, fecha_fin) VALUES (?, ?)";
        try (Connection conn = ConnectionPool.getInstanceConectionPool().getConnectionPool();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setDate(1, period.getStartDate());
            ps.setDate(2, period.getEndDate());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataAccessException("Error saving the school period.", e);
        }
    }

    /**
     * Updates the dates of an existing school period.
     *
     * @param period DTO with the updated dates and the original ID.
     * @return true if the period was updated successfully.
     * @throws DataAccessException If the SQL syntax is invalid.
     */
    @Override
    public boolean update(SchoolPeriodDTO period) throws DataAccessException {
        String query = "UPDATE periodo_escolar SET fecha_inicio = ?, fecha_fin = ? WHERE id_periodo_escolar = ?";
        try (Connection conn = ConnectionPool.getInstanceConectionPool().getConnectionPool();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setDate(1, period.getStartDate());
            ps.setDate(2, period.getEndDate());
            ps.setInt(3, period.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataAccessException("Error updating the school period.", e);
        }
    }

    /**
     * Retrieves a specific school period by its ID.
     *
     * @param id The internal identifier of the school period.
     * @return A SchoolPeriodDTO object, or null if not found.
     * @throws DataAccessException If database connection fails.
     */
    @Override
    public SchoolPeriodDTO getById(int id) throws DataAccessException {
        String query = "SELECT * FROM periodo_escolar WHERE id_periodo_escolar = ?";
        try (Connection conn = ConnectionPool.getInstanceConectionPool().getConnectionPool();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    SchoolPeriodDTO period = new SchoolPeriodDTO();
                    period.setId(rs.getInt("id_periodo_escolar"));
                    period.setStartDate(rs.getDate("fecha_inicio"));
                    period.setEndDate(rs.getDate("fecha_fin"));
                    return period;
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error retrieving the school period.", e);
        }
        return null;
    }

    /**
     * Retrieves all registered school periods.
     *
     * @return List of SchoolPeriodDTOs.
     * @throws DataAccessException If query fails.
     */
    @Override
    public List<SchoolPeriodDTO> getAll() throws DataAccessException {
        List<SchoolPeriodDTO> periods = new ArrayList<>();
        String query = "SELECT * FROM periodo_escolar";
        try (Connection conn = ConnectionPool.getInstanceConectionPool().getConnectionPool();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                SchoolPeriodDTO period = new SchoolPeriodDTO();
                period.setId(rs.getInt("id_periodo_escolar"));
                period.setStartDate(rs.getDate("fecha_inicio"));
                period.setEndDate(rs.getDate("fecha_fin"));
                periods.add(period);
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error retrieving school periods.", e);
        }
        return periods;
    }
}