package spp.data.repository.implementation;


import spp.data.repository.AdministratorDAO;
import spp.domain.dto.AdministratorDTO;
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
 * Implementation for managing system administrators.
 * Supports full CRUD operations to manage administrative access.
 */
public class AdministratorDAOImplementation implements AdministratorDAO {

    /**
     * Saves a new administrator to the database.
     *
     * @param admin DTO containing the administrator details.
     * @return true if successful.
     * @throws PersistenceException If SQL execution fails.
     */
    @Override
    public boolean save(AdministratorDTO admin) throws PersistenceException {
        String query = "INSERT INTO administrador (cuenta_usuario, id_usuario) VALUES (?, ?)";
        try (Connection connection = ConnectionPool.getInstanceConectionPool().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, admin.getUserAccount());
            preparedStatement.setInt(2, admin.getUserId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException | ConfigurationException e) {
            throw new PersistenceException("Error saving the administrator.", e);
        }
    }

    /**
     * Updates an existing administrator's linked user account.
     *
     * @param admin DTO with updated information.
     * @return true if the record was modified.
     * @throws PersistenceException If SQL syntax is incorrect.
     */
    @Override
    public boolean update(AdministratorDTO admin) throws PersistenceException {
        String query = "UPDATE administrador SET cuenta_usuario = ?, id_usuario = ? WHERE id_administrador = ?";
        try (Connection connection = ConnectionPool.getInstanceConectionPool().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, admin.getUserAccount());
            preparedStatement.setInt(2, admin.getUserId());
            preparedStatement.setInt(3, admin.getId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException | ConfigurationException e) {
            throw new PersistenceException("Error updating the administrator.", e);
        }
    }

    /**
     * Retrieves an administrator by their user account.
     *
     * @param userAccount The unique account name.
     * @return AdministratorDTO or null.
     * @throws PersistenceException If query fails.
     */
    @Override
    public AdministratorDTO getByUserAccount(String userAccount) throws PersistenceException {
        String query = "SELECT * FROM administrador WHERE cuenta_usuario = ?";
        try (Connection connection = ConnectionPool.getInstanceConectionPool().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, userAccount);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    AdministratorDTO admin = new AdministratorDTO();
                    admin.setId(resultSet.getInt("id_administrador"));
                    admin.setUserAccount(resultSet.getString("cuenta_usuario"));
                    admin.setUserId(resultSet.getInt("id_usuario"));
                    return admin;
                }
            }
        } catch (SQLException | ConfigurationException e) {
            throw new PersistenceException("Error retrieving the administrator.", e);
        }
        return null;
    }

    /**
     * Retrieves all registered administrators.
     *
     * @return List of AdministratorDTOs.
     * @throws PersistenceException If data extraction fails.
     */
    @Override
    public List<AdministratorDTO> getAll() throws PersistenceException {
        List<AdministratorDTO> admins = new ArrayList<>();
        String query = "SELECT * FROM administrador";
        try (Connection connection = ConnectionPool.getInstanceConectionPool().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                AdministratorDTO admin = new AdministratorDTO();
                admin.setId(resultSet.getInt("id_administrador"));
                admin.setUserAccount(resultSet.getString("cuenta_usuario"));
                admin.setUserId(resultSet.getInt("id_usuario"));
                admins.add(admin);
            }
        } catch (SQLException | ConfigurationException e) {
            throw new PersistenceException("Error retrieving the administrators.", e);
        }
        return admins;
    }
}