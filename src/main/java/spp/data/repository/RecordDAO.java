package spp.data.repository;


import spp.domain.dto.RecordDTO;
import spp.data.exception.PersistenceException;


public interface RecordDAO {
    boolean save(RecordDTO record) throws PersistenceException;
    boolean updateFinalGrade(int recordId, double finalGrade) throws PersistenceException;
    RecordDTO getByInternId(int internId) throws PersistenceException;
}