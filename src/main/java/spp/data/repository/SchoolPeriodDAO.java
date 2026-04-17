package spp.data.repository;


import spp.domain.dto.SchoolPeriodDTO;
import spp.data.exception.PersistenceException;
import java.util.List;


public interface SchoolPeriodDAO {
    boolean save(SchoolPeriodDTO period) throws PersistenceException;
    boolean update(SchoolPeriodDTO period) throws PersistenceException;
    SchoolPeriodDTO getById(int id) throws PersistenceException;
    List<SchoolPeriodDTO> getAll() throws PersistenceException;
}