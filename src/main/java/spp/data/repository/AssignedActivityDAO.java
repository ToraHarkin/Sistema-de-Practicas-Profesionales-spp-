package spp.data.repository;


import spp.domain.dto.AssignedActivityDTO;
import spp.data.exception.DataAccessException;
import java.util.List;



public interface AssignedActivityDAO {
    boolean save(AssignedActivityDTO assignedActivity) throws DataAccessException;
    boolean updateGrade(int id, double grade, String observations) throws DataAccessException;
    List<AssignedActivityDTO> getByInternId(int internId) throws DataAccessException;
}