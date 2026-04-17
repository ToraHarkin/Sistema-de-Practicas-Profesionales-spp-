package spp.data.repository;


import spp.domain.dto.InternDTO;
import spp.data.exception.PersistenceException;
import java.util.List;


public interface InternDAO {
    boolean save(InternDTO intern) throws PersistenceException;
    boolean update(InternDTO intern) throws PersistenceException;
    InternDTO getByEnrollment(String enrollment) throws PersistenceException;
    List<InternDTO> getAll() throws PersistenceException;
}