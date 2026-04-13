package spp.data.repository;
import spp.domain.dto.OrganizationAddressDTO;
import spp.data.exception.DataAccessException;

public interface OrganizationAddressDAO {
    boolean save(OrganizationAddressDTO address) throws DataAccessException;
    boolean update(OrganizationAddressDTO address) throws DataAccessException;
    OrganizationAddressDTO getByOrganizationId(int organizationId) throws DataAccessException;
}