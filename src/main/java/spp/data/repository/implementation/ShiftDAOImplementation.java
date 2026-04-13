package spp.data.repository.implementation;

import spp.data.repository.ShiftDAO;
import spp.domain.dto.ShiftDTO;
import spp.data.exception.DataAccessException;
import spp.data.connection.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation for the academic shifts catalog.
 * Manages operations for shift creation, modification, and retrieval.
 */
public class ShiftDAOImplementation implements ShiftDAO {

    /**
     * Registers a new shift.
     *
     * @param shift DTO containing the shift details.
     * @return true if successful.
     * @throws DataAccessException If SQL fails.
     */
    @Override
    public boolean save(ShiftDTO shift) throws DataAccessException {
        String query = "INSERT INTO turno (nombre) VALUES (?)";
        try (Connection conn = ConnectionPool.getInstanceConectionPool().getConnectionPool();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, shift.getName());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataAccessException("Error saving the shift.", e);
        }
    }

    /**
     * Updates an existing shift name.
     *
     * @param shift DTO containing the updated name and ID.
     * @return true if updated.
     * @throws DataAccessException If execution fails.
     */
    @Override
    public boolean update(ShiftDTO shift) throws DataAccessException {
        String query = "UPDATE turno SET nombre = ? WHERE id_turno = ?";
        try (Connection conn = ConnectionPool.getInstanceConectionPool().getConnectionPool();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, shift.getName());
            ps.setInt(2, shift.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataAccessException("Error updating the shift.", e);
        }
    }

    /**
     * Retrieves a specific shift by its ID.
     *
     * @param id The internal identifier.
     * @return ShiftDTO object or null.
     * @throws DataAccessException If query fails.
     */
    @Override
    public ShiftDTO getById(int id) throws DataAccessException {
        String query = "SELECT * FROM turno WHERE id_turno = ?";
        try (Connection conn = ConnectionPool.getInstanceConectionPool().getConnectionPool();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ShiftDTO shift = new ShiftDTO();
                    shift.setId(rs.getInt("id_turno"));
                    shift.setName(rs.getString("nombre"));
                    return shift;
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error retrieving the shift.", e);
        }
        return null;
    }

    /**
     * Retrieves all available shifts.
     *
     * @return List of ShiftDTOs.
     * @throws DataAccessException If connection is lost.
     */
    @Override
    public List<ShiftDTO> getAll() throws DataAccessException {
        List<ShiftDTO> shifts = new ArrayList<>();
        String query = "SELECT * FROM turno";
        try (Connection conn = ConnectionPool.getInstanceConectionPool().getConnectionPool();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ShiftDTO shift = new ShiftDTO();
                shift.setId(rs.getInt("id_turno"));
                shift.setName(rs.getString("nombre"));
                shifts.add(shift);
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error retrieving shifts.", e);
        }
        return shifts;
    }
}