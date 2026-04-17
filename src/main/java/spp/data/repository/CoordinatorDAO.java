package spp.data.repository;


import spp.domain.dto.CoordinatorDTO;
import spp.data.exception.PersistenceException;
import java.util.List;



public interface CoordinatorDAO {
    boolean save(CoordinatorDTO coordinator) throws PersistenceException;
    boolean update(CoordinatorDTO coordinator) throws PersistenceException;
    CoordinatorDTO getByPersonalNumber(String personalNumber) throws PersistenceException;
    List<CoordinatorDTO> getAll() throws PersistenceException;
}