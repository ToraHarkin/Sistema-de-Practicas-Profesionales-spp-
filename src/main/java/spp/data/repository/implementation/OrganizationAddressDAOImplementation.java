package spp.data.repository.implementation;

import spp.data.repository.OrganizationAddressDAO;
import spp.domain.dto.OrganizationAddressDTO;
import spp.data.exception.DataAccessException;
import spp.data.connection.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Implementation for managing the physical addresses of linked organizations.
 */
public class OrganizationAddressDAOImplementation implements OrganizationAddressDAO {

    /**
     * Saves the address details for an organization.
     *
     * @param address DTO containing location data.
     * @return true if successful.
     * @throws DataAccessException If execution fails.
     */
    @Override
    public boolean save(OrganizationAddressDTO address) throws DataAccessException {
        String query = "INSERT INTO direccion_organizacion (calle, numero_exterior, numero_interior, colonia, codigo_postal, ciudad, pais, id_organizacion_vinculada) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionPool.getInstanceConectionPool().getConnectionPool();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, address.getStreet());
            ps.setString(2, address.getExternalNumber());
            ps.setString(3, address.getInternalNumber()); // Can be null
            ps.setString(4, address.getNeighborhood());
            ps.setString(5, address.getZipCode());
            ps.setString(6, address.getCity());
            ps.setString(7, address.getCountry());
            ps.setInt(8, address.getLinkedOrganizationId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataAccessException("Error saving the organization address.", e);
        }
    }

    /**
     * Updates an existing address.
     *
     * @param address DTO with modified fields.
     * @return true if updated.
     * @throws DataAccessException If SQL syntax is incorrect.
     */
    @Override
    public boolean update(OrganizationAddressDTO address) throws DataAccessException {
        String query = "UPDATE direccion_organizacion SET calle = ?, numero_exterior = ?, numero_interior = ?, colonia = ?, codigo_postal = ?, ciudad = ?, pais = ? WHERE id_direccion_organizacion = ?";
        try (Connection conn = ConnectionPool.getInstanceConectionPool().getConnectionPool();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, address.getStreet());
            ps.setString(2, address.getExternalNumber());
            ps.setString(3, address.getInternalNumber());
            ps.setString(4, address.getNeighborhood());
            ps.setString(5, address.getZipCode());
            ps.setString(6, address.getCity());
            ps.setString(7, address.getCountry());
            ps.setInt(8, address.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataAccessException("Error updating the organization address.", e);
        }
    }

    /**
     * Retrieves the address associated with an organization.
     *
     * @param organizationId Internal ID of the linked organization.
     * @return OrganizationAddressDTO or null if not found.
     * @throws DataAccessException If connection is lost.
     */
    @Override
    public OrganizationAddressDTO getByOrganizationId(int organizationId) throws DataAccessException {
        String query = "SELECT * FROM direccion_organizacion WHERE id_organizacion_vinculada = ?";
        try (Connection conn = ConnectionPool.getInstanceConectionPool().getConnectionPool();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, organizationId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    OrganizationAddressDTO address = new OrganizationAddressDTO();
                    address.setId(rs.getInt("id_direccion_organizacion"));
                    address.setStreet(rs.getString("calle"));
                    address.setExternalNumber(rs.getString("numero_exterior"));
                    address.setInternalNumber(rs.getString("numero_interior"));
                    address.setNeighborhood(rs.getString("colonia"));
                    address.setZipCode(rs.getString("codigo_postal"));
                    address.setCity(rs.getString("ciudad"));
                    address.setCountry(rs.getString("pais"));
                    address.setLinkedOrganizationId(rs.getInt("id_organizacion_vinculada"));
                    return address;
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error retrieving the address.", e);
        }
        return null;
    }
}