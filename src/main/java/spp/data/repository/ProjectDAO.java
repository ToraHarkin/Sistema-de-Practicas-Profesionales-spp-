package spp.data.repository;


import spp.domain.dto.ProjectDTO;
import spp.data.exception.PersistenceException;
import java.util.List;


public interface ProjectDAO {
    boolean save(ProjectDTO project) throws PersistenceException;
    boolean update(ProjectDTO project) throws PersistenceException;
    ProjectDTO getById(int id) throws PersistenceException;
    List<ProjectDTO> getAllAvailable() throws PersistenceException;
}