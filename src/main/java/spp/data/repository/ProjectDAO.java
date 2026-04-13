package spp.data.repository;

import spp.domain.dto.ProjectDTO;
import spp.data.exception.DataAccessException;
import java.util.List;

public interface ProjectDAO {
    boolean save(ProjectDTO project) throws DataAccessException;
    boolean update(ProjectDTO project) throws DataAccessException;
    ProjectDTO getById(int id) throws DataAccessException;
    List<ProjectDTO> getAllAvailable() throws DataAccessException;
}