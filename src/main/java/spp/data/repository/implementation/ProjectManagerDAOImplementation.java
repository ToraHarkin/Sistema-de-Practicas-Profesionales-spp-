package spp.data.repository.implementation;


import spp.data.repository.ProjectManagerDAO;
import spp.domain.dto.ProjectManagerDTO;
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
 * Implementation for managing the external contacts responsible for a project.
 */
public class ProjectManagerDAOImplementation implements ProjectManagerDAO {

    /**
     * Registers a new manager tied to a specific project.
     *
     * @param manager DTO containing contact info.
     * @return true if successful.
     * @throws DataAccessException If the project ID is invalid.
     */
    @Override
    public boolean save(ProjectManagerDTO manager) throws DataAccessException {
        String query = "INSERT INTO responsable_proyecto (nombre, apellido_paterno, apellido_materno, cargo, telefono, correo_electronico, id_proyecto) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = ConnectionPool.getInstanceConectionPool().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, manager.getName());
            preparedStatement.setString(2, manager.getPaternalSurname());
            preparedStatement.setString(3, manager.getMaternalSurname());
            preparedStatement.setString(4, manager.getPosition());
            preparedStatement.setString(5, manager.getPhone());
            preparedStatement.setString(6, manager.getEmail());
            preparedStatement.setInt(7, manager.getProjectId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException | ConfigurationException e) {
            throw new DataAccessException("Error saving the project manager.", e);
        }
    }

    /**
     * Updates an existing project manager's contact info.
     *
     * @param manager DTO with updated information.
     * @return true if updated.
     * @throws DataAccessException If SQL fails.
     */
    @Override
    public boolean update(ProjectManagerDTO manager) throws DataAccessException {
        String query = "UPDATE responsable_proyecto SET nombre = ?, apellido_paterno = ?, apellido_materno = ?, cargo = ?, telefono = ?, correo_electronico = ? WHERE id_responsable_proyecto = ?";
        try (Connection connection = ConnectionPool.getInstanceConectionPool().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, manager.getName());
            preparedStatement.setString(2, manager.getPaternalSurname());
            preparedStatement.setString(3, manager.getMaternalSurname());
            preparedStatement.setString(4, manager.getPosition());
            preparedStatement.setString(5, manager.getPhone());
            preparedStatement.setString(6, manager.getEmail());
            preparedStatement.setInt(7, manager.getId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException | ConfigurationException e) {
            throw new DataAccessException("Error updating the project manager.", e);
        }
    }

    /**
     * Retrieves all managers assigned to a specific project.
     *
     * @param projectId Internal project identifier.
     * @return List of ProjectManagerDTOs.
     * @throws DataAccessException If query fails.
     */
    @Override
    public List<ProjectManagerDTO> getByProjectId(int projectId) throws DataAccessException {
        List<ProjectManagerDTO> managers = new ArrayList<>();
        String query = "SELECT * FROM responsable_proyecto WHERE id_proyecto = ?";
        try (Connection connection = ConnectionPool.getInstanceConectionPool().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, projectId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    ProjectManagerDTO manager = new ProjectManagerDTO();
                    manager.setId(resultSet.getInt("id_responsable_proyecto"));
                    manager.setName(resultSet.getString("nombre"));
                    manager.setPaternalSurname(resultSet.getString("apellido_paterno"));
                    manager.setMaternalSurname(resultSet.getString("apellido_materno"));
                    manager.setPosition(resultSet.getString("cargo"));
                    manager.setPhone(resultSet.getString("telefono"));
                    manager.setEmail(resultSet.getString("correo_electronico"));
                    manager.setProjectId(resultSet.getInt("id_proyecto"));
                    managers.add(manager);
                }
            }
        } catch (SQLException | ConfigurationException e) {
            throw new DataAccessException("Error retrieving project managers.", e);
        }
        return managers;
    }
}