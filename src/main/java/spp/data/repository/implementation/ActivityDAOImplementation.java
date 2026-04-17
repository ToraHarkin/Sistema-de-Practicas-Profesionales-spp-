package spp.data.repository.implementation;


import spp.data.repository.ActivityDAO;
import spp.domain.dto.ActivityDTO;
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
 * Implementation for managing activities created by professors.
 * Allows complete CRUD control over the academic tasks.
 */
public class ActivityDAOImplementation implements ActivityDAO {

    /**
     * Registers a new activity in the system.
     *
     * @param activity DTO containing title, deadline, and description.
     * @return true if successful.
     * @throws PersistenceException If foreign keys are violated.
     */
    @Override
    public boolean save(ActivityDTO activity) throws PersistenceException {
        String query = "INSERT INTO actividad (titulo, fecha_limite, descripcion, id_profesor) VALUES (?, ?, ?, ?)";
        try (Connection connection = ConnectionPool.getInstanceConectionPool().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, activity.getTitle());
            preparedStatement.setTimestamp(2, activity.getDeadline());
            preparedStatement.setString(3, activity.getDescription());
            preparedStatement.setInt(4, activity.getProfessorId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException | ConfigurationException e) {
            throw new PersistenceException("Error saving the activity.", e);
        }
    }

    /**
     * Modifies the parameters of an existing activity.
     *
     * @param activity DTO containing the updated data.
     * @return true if updated.
     * @throws PersistenceException If SQL syntax is incorrect.
     */
    @Override
    public boolean update(ActivityDTO activity) throws PersistenceException {
        String query = "UPDATE actividad SET titulo = ?, fecha_limite = ?, descripcion = ?, id_profesor = ? WHERE id_actividad = ?";
        try (Connection connection = ConnectionPool.getInstanceConectionPool().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, activity.getTitle());
            preparedStatement.setTimestamp(2, activity.getDeadline());
            preparedStatement.setString(3, activity.getDescription());
            preparedStatement.setInt(4, activity.getProfessorId());
            preparedStatement.setInt(5, activity.getId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException | ConfigurationException e) {
            throw new PersistenceException("Error updating the activity.", e);
        }
    }

    /**
     * Retrieves an activity by its specific ID.
     *
     * @param id The internal ID.
     * @return ActivityDTO object or null.
     * @throws PersistenceException If query fails.
     */
    @Override
    public ActivityDTO getById(int id) throws PersistenceException {
        String query = "SELECT * FROM actividad WHERE id_actividad = ?";
        try (Connection connection = ConnectionPool.getInstanceConectionPool().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    ActivityDTO activity = new ActivityDTO();
                    activity.setId(resultSet.getInt("id_actividad"));
                    activity.setTitle(resultSet.getString("titulo"));
                    activity.setDeadline(resultSet.getTimestamp("fecha_limite"));
                    activity.setDescription(resultSet.getString("descripcion"));
                    activity.setProfessorId(resultSet.getInt("id_profesor"));
                    return activity;
                }
            }
        } catch (SQLException | ConfigurationException e) {
            throw new PersistenceException("Error retrieving the activity.", e);
        }
        return null;
    }

    /**
     * Retrieves all activities assigned by a specific professor.
     *
     * @param professorId The internal ID of the professor.
     * @return List of ActivityDTOs.
     * @throws PersistenceException If data extraction fails.
     */
    @Override
    public List<ActivityDTO> getByProfessorId(int professorId) throws PersistenceException {
        List<ActivityDTO> activities = new ArrayList<>();
        String query = "SELECT * FROM actividad WHERE id_profesor = ?";
        try (Connection connection = ConnectionPool.getInstanceConectionPool().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, professorId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    ActivityDTO activity = new ActivityDTO();
                    activity.setId(resultSet.getInt("id_actividad"));
                    activity.setTitle(resultSet.getString("titulo"));
                    activity.setDeadline(resultSet.getTimestamp("fecha_limite"));
                    activity.setDescription(resultSet.getString("descripcion"));
                    activity.setProfessorId(resultSet.getInt("id_profesor"));
                    activities.add(activity);
                }
            }
        } catch (SQLException | ConfigurationException e) {
            throw new PersistenceException("Error retrieving activities by professor.", e);
        }
        return activities;
    }
}