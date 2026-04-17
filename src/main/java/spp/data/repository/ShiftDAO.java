package spp.data.repository;


import spp.domain.dto.ShiftDTO;
import spp.data.exception.PersistenceException;
import java.util.List;


public interface ShiftDAO {
    boolean save(ShiftDTO shift) throws PersistenceException;
    boolean update(ShiftDTO shift) throws PersistenceException;
    ShiftDTO getById(int id) throws PersistenceException;
    List<ShiftDTO> getAll() throws PersistenceException;
}