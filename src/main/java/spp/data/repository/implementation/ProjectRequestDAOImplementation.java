package spp.data.repository.implementation;


import spp.data.repository.ProjectRequestDAO;
import spp.domain.dto.ProjectRequestDTO;
import spp.data.exception.PersistenceException;
import spp.data.connection.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import spp.data.exception.ConfigurationException;


/**
 * Implementation for managing intern project selections.
 */
public class ProjectRequestDAOImplementation implements ProjectRequestDAO {

    /**
     * Registers an intern's choice for a project with a priority level.
     *
     * @param request DTO containing the request configuration.
     * @return true if successful.
     * @throws PersistenceException If a duplicate priority for the same intern is detected.
     */
    @Override
    public boolean save(ProjectRequestDTO request) throws PersistenceException {
        String query = "INSERT INTO solicitud_proyecto (prioridad, id_practicante, id_proyecto) VALUES (?, ?, ?)";
        try (Connection connection = ConnectionPool.getInstanceConectionPool().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, request.getPriority());
            preparedStatement.setInt(2, request.getInternId());
            preparedStatement.setInt(3, request.getProjectId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException | ConfigurationException e) {
            throw new PersistenceException("Error saving the project request.", e);
        }
    }

    /**
     * Removes a project request if the intern changes their mind before assignment.
     *
     * @param requestId The ID of the request to delete.
     * @return true if deleted.
     * @throws PersistenceException If SQL execution fails.
     */
    @Override
    public boolean delete(int requestId) throws PersistenceException {
        String query = "DELETE FROM solicitud_proyecto WHERE id_solicitud_proyecto = ?";
        try (Connection connection = ConnectionPool.getInstanceConectionPool().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, requestId);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException | ConfigurationException e) {
            throw new PersistenceException("Error deleting the project request.", e);
        }
    }

    /**
     * Retrieves all project requests made by an intern to evaluate assignments.
     *
     * @param internId The intern's internal identifier.
     * @return List of ProjectRequestDTOs sorted by priority (implicitly or explicitly).
     * @throws PersistenceException If query fails.
     */
    @Override
    public List<ProjectRequestDTO> getByInternId(int internId) throws PersistenceException {
        List<ProjectRequestDTO> requests = new ArrayList<>();
        String query = "SELECT * FROM solicitud_proyecto WHERE id_practicante = ? ORDER BY prioridad ASC";
        try (Connection connection = ConnectionPool.getInstanceConectionPool().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, internId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    ProjectRequestDTO request = new ProjectRequestDTO();
                    request.setId(resultSet.getInt("id_solicitud_proyecto"));
                    request.setPriority(resultSet.getInt("prioridad"));
                    request.setInternId(resultSet.getInt("id_practicante"));
                    request.setProjectId(resultSet.getInt("id_proyecto"));
                    requests.add(request);
                }
            }
        } catch (SQLException | ConfigurationException e) {
            throw new PersistenceException("Error retrieving project requests.", e);
        }
        return requests;
    }
}