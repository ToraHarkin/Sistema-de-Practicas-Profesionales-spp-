package spp.data.repository;


import spp.domain.dto.SupportDocumentDTO;
import spp.data.exception.PersistenceException;
import java.util.List;


public interface SupportDocumentDAO {
    boolean save(SupportDocumentDTO document) throws PersistenceException;
    boolean updateGrade(int documentId, double grade, String observations) throws PersistenceException;
    List<SupportDocumentDTO> getByInternId(int internId) throws PersistenceException;
}