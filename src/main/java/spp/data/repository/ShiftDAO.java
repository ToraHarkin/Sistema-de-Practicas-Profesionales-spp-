package spp.data.repository;


import spp.domain.dto.ShiftDTO;
import spp.data.exception.DataAccessException;
import java.util.List;


public interface ShiftDAO {
    boolean save(ShiftDTO shift) throws DataAccessException;
    boolean update(ShiftDTO shift) throws DataAccessException;
    ShiftDTO getById(int id) throws DataAccessException;
    List<ShiftDTO> getAll() throws DataAccessException;
}