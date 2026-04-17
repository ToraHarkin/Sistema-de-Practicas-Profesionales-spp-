package spp.data.repository;


import spp.domain.dto.ProjectRequestDTO;
import spp.data.exception.PersistenceException;
import java.util.List;


public interface ProjectRequestDAO {
    boolean save(ProjectRequestDTO request) throws PersistenceException;
    boolean delete(int requestId) throws PersistenceException;
    List<ProjectRequestDTO> getByInternId(int internId) throws PersistenceException;
}