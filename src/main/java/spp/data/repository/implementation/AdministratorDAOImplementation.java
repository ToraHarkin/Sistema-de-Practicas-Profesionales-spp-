package spp.data.repository.implementation;

import spp.data.repository.AdministratorDAO;
import spp.domain.dto.AdministratorDTO;
import spp.data.exception.DataAccessException;
import spp.data.connection.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
     * @throws DataAccessException If SQL execution fails.
     */
    @Override
    public boolean save(AdministratorDTO admin) throws DataAccessException {
        String query = "INSERT INTO administrador (cuenta_usuario, id_usuario) VALUES (?, ?)";
        try (Connection conn = ConnectionPool.getInstanceConectionPool().getConnectionPool();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, admin.getUserAccount());
            ps.setInt(2, admin.getUserId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataAccessException("Error saving the administrator.", e);
        }
    }

    /**
     * Updates an existing administrator's linked user account.
     *
     * @param admin DTO with updated information.
     * @return true if the record was modified.
     * @throws DataAccessException If SQL syntax is incorrect.
     */
    @Override
    public boolean update(AdministratorDTO admin) throws DataAccessException {
        String query = "UPDATE administrador SET cuenta_usuario = ?, id_usuario = ? WHERE id_administrador = ?";
        try (Connection conn = ConnectionPool.getInstanceConectionPool().getConnectionPool();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, admin.getUserAccount());
            ps.setInt(2, admin.getUserId());
            ps.setInt(3, admin.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataAccessException("Error updating the administrator.", e);
        }
    }

    /**
     * Retrieves an administrator by their user account.
     *
     * @param userAccount The unique account name.
     * @return AdministratorDTO or null.
     * @throws DataAccessException If query fails.
     */
    @Override
    public AdministratorDTO getByUserAccount(String userAccount) throws DataAccessException {
        String query = "SELECT * FROM administrador WHERE cuenta_usuario = ?";
        try (Connection conn = ConnectionPool.getInstanceConectionPool().getConnectionPool();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, userAccount);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    AdministratorDTO admin = new AdministratorDTO();
                    admin.setId(rs.getInt("id_administrador"));
                    admin.setUserAccount(rs.getString("cuenta_usuario"));
                    admin.setUserId(rs.getInt("id_usuario"));
                    return admin;
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error retrieving the administrator.", e);
        }
        return null;
    }

    /**
     * Retrieves all registered administrators.
     *
     * @return List of AdministratorDTOs.
     * @throws DataAccessException If data extraction fails.
     */
    @Override
    public List<AdministratorDTO> getAll() throws DataAccessException {
        List<AdministratorDTO> admins = new ArrayList<>();
        String query = "SELECT * FROM administrador";
        try (Connection conn = ConnectionPool.getInstanceConectionPool().getConnectionPool();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                AdministratorDTO admin = new AdministratorDTO();
                admin.setId(rs.getInt("id_administrador"));
                admin.setUserAccount(rs.getString("cuenta_usuario"));
                admin.setUserId(rs.getInt("id_usuario"));
                admins.add(admin);
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error retrieving the administrators.", e);
        }
        return admins;
    }
}