package spp.data.repository;


import spp.domain.dto.AssignedActivityDTO;
import spp.data.exception.PersistenceException;
import java.util.List;



public interface AssignedActivityDAO {
    boolean save(AssignedActivityDTO assignedActivity) throws PersistenceException;
    boolean updateGrade(int id, double grade, String observations) throws PersistenceException;
    List<AssignedActivityDTO> getByInternId(int internId) throws PersistenceException;
}