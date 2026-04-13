package spp.data.repository;
import spp.domain.dto.CoordinatorDTO;
import spp.data.exception.DataAccessException;
import java.util.List;

public interface CoordinatorDAO {
    boolean save(CoordinatorDTO coordinator) throws DataAccessException;
    boolean update(CoordinatorDTO coordinator) throws DataAccessException;
    CoordinatorDTO getByPersonalNumber(String personalNumber) throws DataAccessException;
    List<CoordinatorDTO> getAll() throws DataAccessException;
}