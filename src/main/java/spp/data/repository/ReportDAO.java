package spp.data.repository;

import spp.domain.dto.ReportDTO;
import spp.data.exception.DataAccessException;
import java.util.List;

public interface ReportDAO {
    boolean save(ReportDTO report) throws DataAccessException;
    boolean update(ReportDTO report) throws DataAccessException;
    boolean delete(int reportId) throws DataAccessException;
    ReportDTO getById(int reportId) throws DataAccessException;
    List<ReportDTO> getByInternId(int internId) throws DataAccessException;
}