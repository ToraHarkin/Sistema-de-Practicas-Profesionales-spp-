package spp.data.repository;


import spp.domain.dto.SupportDocumentDTO;
import spp.data.exception.DataAccessException;
import java.util.List;


public interface SupportDocumentDAO {
    boolean save(SupportDocumentDTO document) throws DataAccessException;
    boolean updateGrade(int documentId, double grade, String observations) throws DataAccessException;
    List<SupportDocumentDTO> getByInternId(int internId) throws DataAccessException;
}