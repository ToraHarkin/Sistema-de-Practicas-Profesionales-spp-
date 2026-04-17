package spp.data.repository;


import spp.domain.dto.ActivityDTO;
import spp.data.exception.PersistenceException;
import java.util.List;


public interface ActivityDAO {
    boolean save(ActivityDTO activity) throws PersistenceException;
    boolean update(ActivityDTO activity) throws PersistenceException;
    ActivityDTO getById(int id) throws PersistenceException;
    List<ActivityDTO> getByProfessorId(int professorId) throws PersistenceException;
}