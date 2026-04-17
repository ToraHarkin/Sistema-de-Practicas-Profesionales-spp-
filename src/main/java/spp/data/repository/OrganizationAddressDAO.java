package spp.data.repository;


import spp.domain.dto.OrganizationAddressDTO;
import spp.data.exception.PersistenceException;



public interface OrganizationAddressDAO {
    boolean save(OrganizationAddressDTO address) throws PersistenceException;
    boolean update(OrganizationAddressDTO address) throws PersistenceException;
    OrganizationAddressDTO getByOrganizationId(int organizationId) throws PersistenceException;
}