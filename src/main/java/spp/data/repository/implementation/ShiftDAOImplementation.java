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
        try (Connection connection = ConnectionPool.getInstanceConectionPool().getConnectionPool();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, shift.getName());
            return preparedStatement.executeUpdate() > 0;
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
        try (Connection connection = ConnectionPool.getInstanceConectionPool().getConnectionPool();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, shift.getName());
            preparedStatement.setInt(2, shift.getId());
            return preparedStatement.executeUpdate() > 0;
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
        try (Connection connection = ConnectionPool.getInstanceConectionPool().getConnectionPool();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    ShiftDTO shift = new ShiftDTO();
                    shift.setId(resultSet.getInt("id_turno"));
                    shift.setName(resultSet.getString("nombre"));
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
        try (Connection connection = ConnectionPool.getInstanceConectionPool().getConnectionPool();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                ShiftDTO shift = new ShiftDTO();
                shift.setId(resultSet.getInt("id_turno"));
                shift.setName(resultSet.getString("nombre"));
                shifts.add(shift);
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error retrieving shifts.", e);
        }
        return shifts;
    }
}