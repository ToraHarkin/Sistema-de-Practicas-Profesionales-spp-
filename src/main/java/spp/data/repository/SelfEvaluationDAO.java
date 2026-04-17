package spp.data.repository;


import spp.domain.dto.SelfEvaluationDTO;
import spp.data.exception.PersistenceException;
import java.util.List;


public interface SelfEvaluationDAO {
    boolean save(SelfEvaluationDTO evaluation) throws PersistenceException;
    boolean update(SelfEvaluationDTO evaluation) throws PersistenceException;
    SelfEvaluationDTO getById(int id) throws PersistenceException;
    List<SelfEvaluationDTO> getByInternId(int internId) throws PersistenceException;
}