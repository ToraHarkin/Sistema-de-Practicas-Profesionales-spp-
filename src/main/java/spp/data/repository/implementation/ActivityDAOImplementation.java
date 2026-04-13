package spp.data.repository.implementation;

import spp.data.repository.ActivityDAO;
import spp.domain.dto.ActivityDTO;
import spp.data.exception.DataAccessException;
import spp.data.connection.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation for managing activities created by professors.
 * Allows complete CRUD control over the academic tasks.
 */
public class ActivityDAOImplementation implements ActivityDAO {

    /**
     * Registers a new activity in the system.
     *
     * @param activity DTO containing title, deadline, and description.
     * @return true if successful.
     * @throws DataAccessException If foreign keys are violated.
     */
    @Override
    public boolean save(ActivityDTO activity) throws DataAccessException {
        String query = "INSERT INTO actividad (titulo, fecha_limite, descripcion, id_profesor) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectionPool.getInstanceConectionPool().getConnectionPool();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, activity.getTitle());
            ps.setTimestamp(2, activity.getDeadline());
            ps.setString(3, activity.getDescription());
            ps.setInt(4, activity.getProfessorId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataAccessException("Error saving the activity.", e);
        }
    }

    /**
     * Modifies the parameters of an existing activity.
     *
     * @param activity DTO containing the updated data.
     * @return true if updated.
     * @throws DataAccessException If SQL syntax is incorrect.
     */
    @Override
    public boolean update(ActivityDTO activity) throws DataAccessException {
        String query = "UPDATE actividad SET titulo = ?, fecha_limite = ?, descripcion = ?, id_profesor = ? WHERE id_actividad = ?";
        try (Connection conn = ConnectionPool.getInstanceConectionPool().getConnectionPool();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, activity.getTitle());
            ps.setTimestamp(2, activity.getDeadline());
            ps.setString(3, activity.getDescription());
            ps.setInt(4, activity.getProfessorId());
            ps.setInt(5, activity.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataAccessException("Error updating the activity.", e);
        }
    }

    /**
     * Retrieves an activity by its specific ID.
     *
     * @param id The internal ID.
     * @return ActivityDTO object or null.
     * @throws DataAccessException If query fails.
     */
    @Override
    public ActivityDTO getById(int id) throws DataAccessException {
        String query = "SELECT * FROM actividad WHERE id_actividad = ?";
        try (Connection conn = ConnectionPool.getInstanceConectionPool().getConnectionPool();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ActivityDTO activity = new ActivityDTO();
                    activity.setId(rs.getInt("id_actividad"));
                    activity.setTitle(rs.getString("titulo"));
                    activity.setDeadline(rs.getTimestamp("fecha_limite"));
                    activity.setDescription(rs.getString("descripcion"));
                    activity.setProfessorId(rs.getInt("id_profesor"));
                    return activity;
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error retrieving the activity.", e);
        }
        return null;
    }

    /**
     * Retrieves all activities assigned by a specific professor.
     *
     * @param professorId The internal ID of the professor.
     * @return List of ActivityDTOs.
     * @throws DataAccessException If data extraction fails.
     */
    @Override
    public List<ActivityDTO> getByProfessorId(int professorId) throws DataAccessException {
        List<ActivityDTO> activities = new ArrayList<>();
        String query = "SELECT * FROM actividad WHERE id_profesor = ?";
        try (Connection conn = ConnectionPool.getInstanceConectionPool().getConnectionPool();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, professorId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ActivityDTO activity = new ActivityDTO();
                    activity.setId(rs.getInt("id_actividad"));
                    activity.setTitle(rs.getString("titulo"));
                    activity.setDeadline(rs.getTimestamp("fecha_limite"));
                    activity.setDescription(rs.getString("descripcion"));
                    activity.setProfessorId(rs.getInt("id_profesor"));
                    activities.add(activity);
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error retrieving activities by professor.", e);
        }
        return activities;
    }
}