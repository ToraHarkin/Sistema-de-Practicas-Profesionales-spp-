package spp.data.repository;


import spp.domain.dto.RecordDTO;
import spp.data.exception.DataAccessException;


public interface RecordDAO {
    boolean save(RecordDTO record) throws DataAccessException;
    boolean updateFinalGrade(int recordId, double finalGrade) throws DataAccessException;
    RecordDTO getByInternId(int internId) throws DataAccessException;
}