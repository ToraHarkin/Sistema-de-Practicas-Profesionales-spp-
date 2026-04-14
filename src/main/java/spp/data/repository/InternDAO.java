package spp.data.repository;


import spp.domain.dto.InternDTO;
import spp.data.exception.DataAccessException;
import java.util.List;


public interface InternDAO {
    boolean save(InternDTO intern) throws DataAccessException;
    boolean update(InternDTO intern) throws DataAccessException;
    InternDTO getByEnrollment(String enrollment) throws DataAccessException;
    List<InternDTO> getAll() throws DataAccessException;
}