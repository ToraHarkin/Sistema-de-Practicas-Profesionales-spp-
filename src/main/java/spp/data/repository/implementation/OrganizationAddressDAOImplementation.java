package spp.data.repository.implementation;


import spp.data.repository.OrganizationAddressDAO;
import spp.domain.dto.OrganizationAddressDTO;
import spp.data.exception.PersistenceException;
import spp.data.connection.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import spp.data.exception.ConfigurationException;


/**
 * Implementation for managing the physical addresses of linked organizations.
 */
public class OrganizationAddressDAOImplementation implements OrganizationAddressDAO {

    /**
     * Saves the address details for an organization.
     *
     * @param address DTO containing location data.
     * @return true if successful.
     * @throws PersistenceException If execution fails.
     */
    @Override
    public boolean save(OrganizationAddressDTO address) throws PersistenceException {
        String query = "INSERT INTO direccion_organizacion (calle, numero_exterior, numero_interior, colonia, codigo_postal, ciudad, pais, id_organizacion_vinculada) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = ConnectionPool.getInstanceConectionPool().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, address.getStreet());
            preparedStatement.setString(2, address.getExternalNumber());
            preparedStatement.setString(3, address.getInternalNumber()); // Can be null
            preparedStatement.setString(4, address.getNeighborhood());
            preparedStatement.setString(5, address.getZipCode());
            preparedStatement.setString(6, address.getCity());
            preparedStatement.setString(7, address.getCountry());
            preparedStatement.setInt(8, address.getLinkedOrganizationId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException | ConfigurationException e) {
            throw new PersistenceException("Error saving the organization address.", e);
        }
    }

    /**
     * Updates an existing address.
     *
     * @param address DTO with modified fields.
     * @return true if updated.
     * @throws PersistenceException If SQL syntax is incorrect.
     */
    @Override
    public boolean update(OrganizationAddressDTO address) throws PersistenceException {
        String query = "UPDATE direccion_organizacion SET calle = ?, numero_exterior = ?, numero_interior = ?, colonia = ?, codigo_postal = ?, ciudad = ?, pais = ? WHERE id_direccion_organizacion = ?";
        try (Connection connection = ConnectionPool.getInstanceConectionPool().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, address.getStreet());
            preparedStatement.setString(2, address.getExternalNumber());
            preparedStatement.setString(3, address.getInternalNumber());
            preparedStatement.setString(4, address.getNeighborhood());
            preparedStatement.setString(5, address.getZipCode());
            preparedStatement.setString(6, address.getCity());
            preparedStatement.setString(7, address.getCountry());
            preparedStatement.setInt(8, address.getId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException | ConfigurationException e) {
            throw new PersistenceException("Error updating the organization address.", e);
        }
    }

    /**
     * Retrieves the address associated with an organization.
     *
     * @param organizationId Internal ID of the linked organization.
     * @return OrganizationAddressDTO or null if not found.
     * @throws PersistenceException If connection is lost.
     */
    @Override
    public OrganizationAddressDTO getByOrganizationId(int organizationId) throws PersistenceException {
        String query = "SELECT * FROM direccion_organizacion WHERE id_organizacion_vinculada = ?";
        try (Connection connection = ConnectionPool.getInstanceConectionPool().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, organizationId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    OrganizationAddressDTO address = new OrganizationAddressDTO();
                    address.setId(resultSet.getInt("id_direccion_organizacion"));
                    address.setStreet(resultSet.getString("calle"));
                    address.setExternalNumber(resultSet.getString("numero_exterior"));
                    address.setInternalNumber(resultSet.getString("numero_interior"));
                    address.setNeighborhood(resultSet.getString("colonia"));
                    address.setZipCode(resultSet.getString("codigo_postal"));
                    address.setCity(resultSet.getString("ciudad"));
                    address.setCountry(resultSet.getString("pais"));
                    address.setLinkedOrganizationId(resultSet.getInt("id_organizacion_vinculada"));
                    return address;
                }
            }
        } catch (SQLException | ConfigurationException e) {
            throw new PersistenceException("Error retrieving the address.", e);
        }
        return null;
    }
}