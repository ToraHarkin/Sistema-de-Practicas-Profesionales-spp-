package spp.data.repository;


import spp.domain.dto.SchoolPeriodDTO;
import spp.data.exception.DataAccessException;
import java.util.List;


public interface SchoolPeriodDAO {
    boolean save(SchoolPeriodDTO period) throws DataAccessException;
    boolean update(SchoolPeriodDTO period) throws DataAccessException;
    SchoolPeriodDTO getById(int id) throws DataAccessException;
    List<SchoolPeriodDTO> getAll() throws DataAccessException;
}