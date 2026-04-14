package spp.data.repository;


import spp.domain.dto.ActivityDTO;
import spp.data.exception.DataAccessException;
import java.util.List;


public interface ActivityDAO {
    boolean save(ActivityDTO activity) throws DataAccessException;
    boolean update(ActivityDTO activity) throws DataAccessException;
    ActivityDTO getById(int id) throws DataAccessException;
    List<ActivityDTO> getByProfessorId(int professorId) throws DataAccessException;
}