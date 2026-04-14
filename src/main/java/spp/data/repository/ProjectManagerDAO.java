package spp.data.repository;

import spp.domain.dto.ProjectManagerDTO;
import spp.data.exception.DataAccessException;
import java.util.List;


public interface ProjectManagerDAO {
    boolean save(ProjectManagerDTO manager) throws DataAccessException;
    boolean update(ProjectManagerDTO manager) throws DataAccessException;
    List<ProjectManagerDTO> getByProjectId(int projectId) throws DataAccessException;
}