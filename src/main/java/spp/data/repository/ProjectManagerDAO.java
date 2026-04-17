package spp.data.repository;

import spp.domain.dto.ProjectManagerDTO;
import spp.data.exception.PersistenceException;
import java.util.List;


public interface ProjectManagerDAO {
    boolean save(ProjectManagerDTO manager) throws PersistenceException;
    boolean update(ProjectManagerDTO manager) throws PersistenceException;
    List<ProjectManagerDTO> getByProjectId(int projectId) throws PersistenceException;
}