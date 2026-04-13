package spp.data.repository;
import spp.domain.dto.SelfEvaluationDTO;
import spp.data.exception.DataAccessException;
import java.util.List;

public interface SelfEvaluationDAO {
    boolean save(SelfEvaluationDTO evaluation) throws DataAccessException;
    boolean update(SelfEvaluationDTO evaluation) throws DataAccessException;
    SelfEvaluationDTO getById(int id) throws DataAccessException;
    List<SelfEvaluationDTO> getByInternId(int internId) throws DataAccessException;
}