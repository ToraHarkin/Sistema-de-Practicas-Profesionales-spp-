package spp.data.repository;


import spp.domain.dto.AdministratorDTO;
import spp.data.exception.PersistenceException;
import java.util.List;



public interface AdministratorDAO {
    boolean save(AdministratorDTO admin) throws PersistenceException;
    boolean update(AdministratorDTO admin) throws PersistenceException;
    AdministratorDTO getByUserAccount(String userAccount) throws PersistenceException;
    List<AdministratorDTO> getAll() throws PersistenceException;
}