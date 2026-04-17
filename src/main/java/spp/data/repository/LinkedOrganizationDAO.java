package spp.data.repository;


import spp.domain.dto.LinkedOrganizationDTO;
import spp.data.exception.PersistenceException;
import java.util.List;


public interface LinkedOrganizationDAO {
    boolean save(LinkedOrganizationDTO organization) throws PersistenceException;
    boolean update(LinkedOrganizationDTO organization) throws PersistenceException;
    LinkedOrganizationDTO getById(int id) throws PersistenceException;
    List<LinkedOrganizationDTO> getAll() throws PersistenceException;
}
