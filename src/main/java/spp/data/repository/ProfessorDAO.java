package spp.data.repository;


import spp.domain.dto.ProfessorDTO;
import spp.data.exception.DataAccessException;
import java.util.List;


public interface ProfessorDAO {
    boolean save(ProfessorDTO professor) throws DataAccessException;
    boolean update(ProfessorDTO professor) throws DataAccessException;
    ProfessorDTO getByPersonalNumber(String personalNumber) throws DataAccessException;
    List<ProfessorDTO> getAll() throws DataAccessException;
}