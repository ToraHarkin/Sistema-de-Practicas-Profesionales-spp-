package spp.data.repository.implementation;

import spp.data.repository.ProjectDAO;
import spp.domain.dto.ProjectDTO;
import spp.data.exception.DataAccessException;
import spp.data.connection.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the ProjectDAO interface responsible for the lifecycle of linked projects.
 */
public class ProjectDAOImplementation implements ProjectDAO {

    /**
     * Creates a new record for a project offered by an organization.
     *
     * @param project The object encapsulating the information of the project to be registered.
     * @return true if the project was successfully saved in the relational schema.
     * @throws DataAccessException If any foreign key constraint is violated.
     */
    @Override
    public boolean save(ProjectDTO project) throws DataAccessException {
        String query = "INSERT INTO proyecto (nombre, capacidad_practicantes, disponibilidad, descripcion, id_organizacion_vinculada) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = ConnectionPool.getInstanceConectionPool().getConnectionPool();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, project.getName());
            preparedStatement.setInt(2, project.getCapacity());
            preparedStatement.setInt(3, project.getAvailability());
            preparedStatement.setString(4, project.getDescription());
            preparedStatement.setInt(5, project.getLinkedOrganizationId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataAccessException("Error attempting to register the new project.", e);
        }
    }

    /**
     * Overwrites the general details of an existing project.
     *
     * @param project The DTO object modified by the user. The ID must not be altered.
     * @return true if the modification impacted the database; false otherwise.
     * @throws DataAccessException If an SQL syntax error occurs.
     */
    @Override
    public boolean update(ProjectDTO project) throws DataAccessException {
        String query = "UPDATE proyecto SET nombre = ?, capacidad_practicantes = ?, disponibilidad = ?, descripcion = ? WHERE id_proyecto = ?";
        try (Connection connection = ConnectionPool.getInstanceConectionPool().getConnectionPool();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, project.getName());
            preparedStatement.setInt(2, project.getCapacity());
            preparedStatement.setInt(3, project.getAvailability());
            preparedStatement.setString(4, project.getDescription());
            preparedStatement.setInt(5, project.getId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataAccessException("Error attempting to modify the project details.", e);
        }
    }

    /**
     * Locates a specific project using its internal identifier.
     *
     * @param id The auto-incremental identifier of the project.
     * @return The corresponding ProjectDTO object or null if there are no matches.
     * @throws DataAccessException If there is a problem mapping the ResultSet.
     */
    @Override
    public ProjectDTO getById(int id) throws DataAccessException {
        String query = "SELECT * FROM proyecto WHERE id_proyecto = ?";
        try (Connection connection = ConnectionPool.getInstanceConectionPool().getConnectionPool();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    ProjectDTO project = new ProjectDTO();
                    project.setId(resultSet.getInt("id_proyecto"));
                    project.setName(resultSet.getString("nombre"));
                    project.setCapacity(resultSet.getInt("capacidad_practicantes"));
                    project.setAvailability(resultSet.getInt("disponibilidad"));
                    project.setDescription(resultSet.getString("descripcion"));
                    project.setLinkedOrganizationId(resultSet.getInt("id_organizacion_vinculada"));
                    return project;
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error retrieving the requested project information.", e);
        }
        return null;
    }

    /**
     * Generates a filtered catalog with all projects ready to receive interns.
     *
     * @return A list of ProjectDTOs whose availability status is active (1).
     * @throws DataAccessException If the execution of the query is interrupted.
     */
    @Override
    public List<ProjectDTO> getAllAvailable() throws DataAccessException {
        List<ProjectDTO> projects = new ArrayList<>();
        String query = "SELECT * FROM proyecto WHERE disponibilidad = 1";
        try (Connection connection = ConnectionPool.getInstanceConectionPool().getConnectionPool();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                ProjectDTO project = new ProjectDTO();
                project.setId(resultSet.getInt("id_proyecto"));
                project.setName(resultSet.getString("nombre"));
                project.setCapacity(resultSet.getInt("capacidad_practicantes"));
                project.setAvailability(resultSet.getInt("disponibilidad"));
                project.setDescription(resultSet.getString("descripcion"));
                project.setLinkedOrganizationId(resultSet.getInt("id_organizacion_vinculada"));
                projects.add(project);
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error compiling the list of available projects.", e);
        }
        return projects;
    }
}
