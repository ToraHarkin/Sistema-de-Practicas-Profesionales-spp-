package spp.data.repository.implementation;

import spp.data.repository.LinkedOrganizationDAO;
import spp.domain.dto.LinkedOrganizationDTO;
import spp.data.exception.DataAccessException;
import spp.data.connection.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the LinkedOrganizationDAO interface.
 * Handles the persistence of companies or institutions linked to the projects.
 */
public class LinkedOrganizationDAOImplementation implements LinkedOrganizationDAO {

    /**
     * Registers a new linked organization in the database.
     *
     * @param organization DTO containing the organization's information.
     * @return true if the insertion was successful; false otherwise.
     * @throws DataAccessException If a database constraint is violated (e.g. duplicate email).
     */
    @Override
    public boolean save(LinkedOrganizationDTO organization) throws DataAccessException {
        String query = "INSERT INTO organizacion_vinculada (nombre, telefono, correo_electronico, sector) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectionPool.getInstanceConectionPool().getConnectionPool();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, organization.getName());
            ps.setString(2, organization.getPhone());
            ps.setString(3, organization.getEmail());
            ps.setString(4, organization.getSector());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataAccessException("Error saving the linked organization.", e);
        }
    }

    /**
     * Updates an existing linked organization's contact details.
     *
     * @param organization DTO with updated information. ID must be present.
     * @return true if the record was updated.
     * @throws DataAccessException If the SQL execution fails.
     */
    @Override
    public boolean update(LinkedOrganizationDTO organization) throws DataAccessException {
        String query = "UPDATE organizacion_vinculada SET nombre = ?, telefono = ?, correo_electronico = ?, sector = ? WHERE id_organizacion_vinculada = ?";
        try (Connection conn = ConnectionPool.getInstanceConectionPool().getConnectionPool();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, organization.getName());
            ps.setString(2, organization.getPhone());
            ps.setString(3, organization.getEmail());
            ps.setString(4, organization.getSector());
            ps.setInt(5, organization.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataAccessException("Error updating the linked organization.", e);
        }
    }

    /**
     * Retrieves an organization by its internal ID.
     *
     * @param id The primary key of the organization.
     * @return LinkedOrganizationDTO object or null if not found.
     * @throws DataAccessException If communication with the database is lost.
     */
    @Override
    public LinkedOrganizationDTO getById(int id) throws DataAccessException {
        String query = "SELECT * FROM organizacion_vinculada WHERE id_organizacion_vinculada = ?";
        try (Connection conn = ConnectionPool.getInstanceConectionPool().getConnectionPool();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    LinkedOrganizationDTO org = new LinkedOrganizationDTO();
                    org.setId(rs.getInt("id_organizacion_vinculada"));
                    org.setName(rs.getString("nombre"));
                    org.setPhone(rs.getString("telefono"));
                    org.setEmail(rs.getString("correo_electronico"));
                    org.setSector(rs.getString("sector"));
                    return org;
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error retrieving the linked organization.", e);
        }
        return null;
    }

    /**
     * Retrieves a full list of all registered organizations.
     *
     * @return A list of LinkedOrganizationDTOs.
     * @throws DataAccessException If the query cannot be executed.
     */
    @Override
    public List<LinkedOrganizationDTO> getAll() throws DataAccessException {
        List<LinkedOrganizationDTO> organizations = new ArrayList<>();
        String query = "SELECT * FROM organizacion_vinculada";
        try (Connection conn = ConnectionPool.getInstanceConectionPool().getConnectionPool();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                LinkedOrganizationDTO org = new LinkedOrganizationDTO();
                org.setId(rs.getInt("id_organizacion_vinculada"));
                org.setName(rs.getString("nombre"));
                org.setPhone(rs.getString("telefono"));
                org.setEmail(rs.getString("correo_electronico"));
                org.setSector(rs.getString("sector"));
                organizations.add(org);
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error retrieving the list of linked organizations.", e);
        }
        return organizations;
    }
}