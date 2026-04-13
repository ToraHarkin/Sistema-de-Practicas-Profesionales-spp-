package spp.data.repository.implementation;

import spp.data.repository.CoordinatorDAO;
import spp.domain.dto.CoordinatorDTO;
import spp.data.exception.DataAccessException;
import spp.data.connection.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation for managing system coordinators.
 */
public class CoordinatorDAOImplementation implements CoordinatorDAO {

    /**
     * Registers a new coordinator in the system.
     *
     * @param coordinator DTO containing the coordinator's details.
     * @return true if successful.
     * @throws DataAccessException If database communication fails.
     */
    @Override
    public boolean save(CoordinatorDTO coordinator) throws DataAccessException {
        String query = "INSERT INTO coordinador (numero_personal, nombre, apellido_paterno, apellido_materno, tiempo_servicio_meses, id_usuario) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = ConnectionPool.getInstanceConectionPool().getConnectionPool();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, coordinator.getPersonalNumber());
            preparedStatement.setString(2, coordinator.getName());
            preparedStatement.setString(3, coordinator.getPaternalSurname());
            preparedStatement.setString(4, coordinator.getMaternalSurname());
            preparedStatement.setInt(5, coordinator.getMonthsOfService());
            preparedStatement.setInt(6, coordinator.getUserId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataAccessException("Error saving the coordinator.", e);
        }
    }

    /**
     * Updates an existing coordinator's information.
     *
     * @param coordinator DTO with updated data.
     * @return true if updated.
     * @throws DataAccessException If SQL execution fails.
     */
    @Override
    public boolean update(CoordinatorDTO coordinator) throws DataAccessException {
        String query = "UPDATE coordinador SET nombre = ?, apellido_paterno = ?, apellido_materno = ?, tiempo_servicio_meses = ? WHERE numero_personal = ?";
        try (Connection connection = ConnectionPool.getInstanceConectionPool().getConnectionPool();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, coordinator.getName());
            preparedStatement.setString(2, coordinator.getPaternalSurname());
            preparedStatement.setString(3, coordinator.getMaternalSurname());
            preparedStatement.setInt(4, coordinator.getMonthsOfService());
            preparedStatement.setString(5, coordinator.getPersonalNumber());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataAccessException("Error updating the coordinator.", e);
        }
    }

    /**
     * Retrieves a coordinator using their unique personal number.
     *
     * @param personalNumber The university identification number.
     * @return CoordinatorDTO object or null if not found.
     * @throws DataAccessException If query fails.
     */
    @Override
    public CoordinatorDTO getByPersonalNumber(String personalNumber) throws DataAccessException {
        String query = "SELECT * FROM coordinador WHERE numero_personal = ?";
        try (Connection connection = ConnectionPool.getInstanceConectionPool().getConnectionPool();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, personalNumber);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    CoordinatorDTO coordinator = new CoordinatorDTO();
                    coordinator.setId(resultSet.getInt("id_coordinador"));
                    coordinator.setPersonalNumber(resultSet.getString("numero_personal"));
                    coordinator.setName(resultSet.getString("nombre"));
                    coordinator.setPaternalSurname(resultSet.getString("apellido_paterno"));
                    coordinator.setMaternalSurname(resultSet.getString("apellido_materno"));
                    coordinator.setMonthsOfService(resultSet.getInt("tiempo_servicio_meses"));
                    coordinator.setUserId(resultSet.getInt("id_usuario"));
                    return coordinator;
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error retrieving the coordinator.", e);
        }
        return null;
    }

    /**
     * Retrieves a list of all coordinators.
     *
     * @return List of CoordinatorDTOs.
     * @throws DataAccessException If data extraction fails.
     */
    @Override
    public List<CoordinatorDTO> getAll() throws DataAccessException {
        List<CoordinatorDTO> coordinators = new ArrayList<>();
        String query = "SELECT * FROM coordinador";
        try (Connection connection = ConnectionPool.getInstanceConectionPool().getConnectionPool();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                CoordinatorDTO coordinator = new CoordinatorDTO();
                coordinator.setId(resultSet.getInt("id_coordinador"));
                coordinator.setPersonalNumber(resultSet.getString("numero_personal"));
                coordinator.setName(resultSet.getString("nombre"));
                coordinator.setPaternalSurname(resultSet.getString("apellido_paterno"));
                coordinator.setMaternalSurname(resultSet.getString("apellido_materno"));
                coordinator.setMonthsOfService(resultSet.getInt("tiempo_servicio_meses"));
                coordinator.setUserId(resultSet.getInt("id_usuario"));
                coordinators.add(coordinator);
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error retrieving coordinators.", e);
        }
        return coordinators;
    }
}