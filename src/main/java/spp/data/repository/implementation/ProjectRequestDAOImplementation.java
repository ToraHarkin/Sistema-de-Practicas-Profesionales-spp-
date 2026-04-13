package spp.data.repository.implementation;

import spp.data.repository.ProjectRequestDAO;
import spp.domain.dto.ProjectRequestDTO;
import spp.data.exception.DataAccessException;
import spp.data.connection.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation for managing intern project selections.
 */
public class ProjectRequestDAOImplementation implements ProjectRequestDAO {

    /**
     * Registers an intern's choice for a project with a priority level.
     *
     * @param request DTO containing the request configuration.
     * @return true if successful.
     * @throws DataAccessException If a duplicate priority for the same intern is detected.
     */
    @Override
    public boolean save(ProjectRequestDTO request) throws DataAccessException {
        String query = "INSERT INTO solicitud_proyecto (prioridad, id_practicante, id_proyecto) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionPool.getInstanceConectionPool().getConnectionPool();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, request.getPriority());
            ps.setInt(2, request.getInternId());
            ps.setInt(3, request.getProjectId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataAccessException("Error saving the project request.", e);
        }
    }

    /**
     * Removes a project request if the intern changes their mind before assignment.
     *
     * @param requestId The ID of the request to delete.
     * @return true if deleted.
     * @throws DataAccessException If SQL execution fails.
     */
    @Override
    public boolean delete(int requestId) throws DataAccessException {
        String query = "DELETE FROM solicitud_proyecto WHERE id_solicitud_proyecto = ?";
        try (Connection conn = ConnectionPool.getInstanceConectionPool().getConnectionPool();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, requestId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataAccessException("Error deleting the project request.", e);
        }
    }

    /**
     * Retrieves all project requests made by an intern to evaluate assignments.
     *
     * @param internId The intern's internal identifier.
     * @return List of ProjectRequestDTOs sorted by priority (implicitly or explicitly).
     * @throws DataAccessException If query fails.
     */
    @Override
    public List<ProjectRequestDTO> getByInternId(int internId) throws DataAccessException {
        List<ProjectRequestDTO> requests = new ArrayList<>();
        String query = "SELECT * FROM solicitud_proyecto WHERE id_practicante = ? ORDER BY prioridad ASC";
        try (Connection conn = ConnectionPool.getInstanceConectionPool().getConnectionPool();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, internId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ProjectRequestDTO request = new ProjectRequestDTO();
                    request.setId(rs.getInt("id_solicitud_proyecto"));
                    request.setPriority(rs.getInt("prioridad"));
                    request.setInternId(rs.getInt("id_practicante"));
                    request.setProjectId(rs.getInt("id_proyecto"));
                    requests.add(request);
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error retrieving project requests.", e);
        }
        return requests;
    }
}