package spp.data.repository;


import spp.domain.dto.ReportDTO;
import spp.data.exception.PersistenceException;
import java.util.List;


public interface ReportDAO {
    boolean save(ReportDTO report) throws PersistenceException;
    boolean update(ReportDTO report) throws PersistenceException;
    boolean delete(int reportId) throws PersistenceException;
    ReportDTO getById(int reportId) throws PersistenceException;
    List<ReportDTO> getByInternId(int internId) throws PersistenceException;
}