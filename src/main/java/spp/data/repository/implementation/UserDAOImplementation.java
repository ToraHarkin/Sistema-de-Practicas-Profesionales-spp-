package spp.data.repository.implementation;


import spp.data.repository.UserDAO;
import spp.domain.dto.UserDTO;
import spp.data.exception.PersistenceException;
import spp.data.connection.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import spp.data.exception.ConfigurationException;


/**
 * Implementation of the UserDAO interface for managing system credentials and statuses.
 */
public class UserDAOImplementation implements UserDAO {

    /**
     * Saves a new user applying an automatic timestamp for their registration date.
     *
     * @param user Transfer object containing the account, encrypted password, and initial status.
     * @return true if the user was created successfully; false otherwise.
     * @throws PersistenceException If the database rejects the insertion due to technical conflicts.
     */
    @Override
    public boolean save(UserDTO user) throws PersistenceException {
        String query = "INSERT INTO usuario (contraseña, cuenta, estado, fecha_registro) VALUES (?, ?, ?, NOW())";
        try (Connection connection = ConnectionPool.getInstanceConectionPool().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setString(2, user.getAccount());
            preparedStatement.setString(3, user.getStatus());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException | ConfigurationException e) {
            throw new PersistenceException("Error registering the new user account.", e);
        }
    }

    /**
     * Searches for a user based on their account name for authentication processes.
     *
     * @param account The unique username (account) in the system.
     * @return A retrieved UserDTO object, or null if the credentials do not match.
     * @throws PersistenceException If communication with the MySQL server is lost.
     */
    @Override
    public UserDTO getByAccount(String account) throws PersistenceException {
        String query = "SELECT * FROM usuario WHERE cuenta = ?";
        try (Connection connection = ConnectionPool.getInstanceConectionPool().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, account);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    UserDTO user = new UserDTO();
                    user.setId(resultSet.getInt("id_usuario"));
                    user.setPassword(resultSet.getString("contraseña"));
                    user.setAccount(resultSet.getString("cuenta"));
                    user.setStatus(resultSet.getString("estado"));
                    return user;
                }
            }
        } catch (SQLException | ConfigurationException e) {
            throw new PersistenceException("Error verifying the existence of the user account.", e);
        }
        return null;
    }

    /**
     * Executes a logical deletion or reactivation by modifying the user's status.
     *
     * @param userId The unique internal identifier of the user.
     * @param status The new status to be assigned (e.g., "Activo", "Inactivo").
     * @return true if the status change was applied; false if the user does not exist.
     * @throws PersistenceException If an error occurs during the update process.
     */
    @Override
    public boolean updateStatus(int userId, String status) throws PersistenceException {
        String query = "UPDATE usuario SET estado = ? WHERE id_usuario = ?";
        try (Connection connection = ConnectionPool.getInstanceConectionPool().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, userId);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException | ConfigurationException e) {
            throw new PersistenceException("Error processing the user's status change.", e);
        }
    }
}