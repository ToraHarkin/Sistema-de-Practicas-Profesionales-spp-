package spp.data.repository;


import spp.domain.dto.ProfessorDTO;
import spp.data.exception.PersistenceException;
import java.util.List;


public interface ProfessorDAO {
    boolean save(ProfessorDTO professor) throws PersistenceException;
    boolean update(ProfessorDTO professor) throws PersistenceException;
    ProfessorDTO getByPersonalNumber(String personalNumber) throws PersistenceException;
    List<ProfessorDTO> getAll() throws PersistenceException;
    boolean inactivate(String personalNumber) throws PersistenceException;
}