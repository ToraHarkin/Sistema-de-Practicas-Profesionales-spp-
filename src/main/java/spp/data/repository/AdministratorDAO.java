package spp.data.repository;


import spp.domain.dto.AdministratorDTO;
import spp.data.exception.DataAccessException;
import java.util.List;



public interface AdministratorDAO {
    boolean save(AdministratorDTO admin) throws DataAccessException;
    boolean update(AdministratorDTO admin) throws DataAccessException;
    AdministratorDTO getByUserAccount(String userAccount) throws DataAccessException;
    List<AdministratorDTO> getAll() throws DataAccessException;
}