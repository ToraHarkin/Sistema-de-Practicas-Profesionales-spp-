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
import spp.data.exception.ConfigurationException;


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
        try (Connection connection = ConnectionPool.getInstanceConectionPool().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDate(1, period.getStartDate());
            preparedStatement.setDate(2, period.getEndDate());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException | ConfigurationException e) {
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
        try (Connection connection = ConnectionPool.getInstanceConectionPool().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDate(1, period.getStartDate());
            preparedStatement.setDate(2, period.getEndDate());
            preparedStatement.setInt(3, period.getId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException| ConfigurationException e) {
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
        try (Connection connection = ConnectionPool.getInstanceConectionPool().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    SchoolPeriodDTO period = new SchoolPeriodDTO();
                    period.setId(resultSet.getInt("id_periodo_escolar"));
                    period.setStartDate(resultSet.getDate("fecha_inicio"));
                    period.setEndDate(resultSet.getDate("fecha_fin"));
                    return period;
                }
            }
        } catch (SQLException | ConfigurationException e) {
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
        try (Connection connection = ConnectionPool.getInstanceConectionPool().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                SchoolPeriodDTO period = new SchoolPeriodDTO();
                period.setId(resultSet.getInt("id_periodo_escolar"));
                period.setStartDate(resultSet.getDate("fecha_inicio"));
                period.setEndDate(resultSet.getDate("fecha_fin"));
                periods.add(period);
            }
        } catch (SQLException | ConfigurationException e) {
            throw new DataAccessException("Error retrieving school periods.", e);
        }
        return periods;
    }
}