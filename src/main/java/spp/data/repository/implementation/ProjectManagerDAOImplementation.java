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
        try (Connection conn = ConnectionPool.getInstanceConectionPool().getConnectionPool();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, manager.getName());
            ps.setString(2, manager.getPaternalSurname());
            ps.setString(3, manager.getMaternalSurname());
            ps.setString(4, manager.getPosition());
            ps.setString(5, manager.getPhone());
            ps.setString(6, manager.getEmail());
            ps.setInt(7, manager.getProjectId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
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
        try (Connection conn = ConnectionPool.getInstanceConectionPool().getConnectionPool();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, manager.getName());
            ps.setString(2, manager.getPaternalSurname());
            ps.setString(3, manager.getMaternalSurname());
            ps.setString(4, manager.getPosition());
            ps.setString(5, manager.getPhone());
            ps.setString(6, manager.getEmail());
            ps.setInt(7, manager.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
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
        try (Connection conn = ConnectionPool.getInstanceConectionPool().getConnectionPool();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, projectId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ProjectManagerDTO manager = new ProjectManagerDTO();
                    manager.setId(rs.getInt("id_responsable_proyecto"));
                    manager.setName(rs.getString("nombre"));
                    manager.setPaternalSurname(rs.getString("apellido_paterno"));
                    manager.setMaternalSurname(rs.getString("apellido_materno"));
                    manager.setPosition(rs.getString("cargo"));
                    manager.setPhone(rs.getString("telefono"));
                    manager.setEmail(rs.getString("correo_electronico"));
                    manager.setProjectId(rs.getInt("id_proyecto"));
                    managers.add(manager);
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error retrieving project managers.", e);
        }
        return managers;
    }
}