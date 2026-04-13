package spp.data.repository;

import spp.domain.dto.ProjectRequestDTO;
import spp.data.exception.DataAccessException;
import java.util.List;

public interface ProjectRequestDAO {
    boolean save(ProjectRequestDTO request) throws DataAccessException;
    boolean delete(int requestId) throws DataAccessException;
    List<ProjectRequestDTO> getByInternId(int internId) throws DataAccessException;
}