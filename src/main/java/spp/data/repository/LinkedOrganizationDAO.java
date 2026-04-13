package spp.data.repository;

import spp.domain.dto.LinkedOrganizationDTO;
import spp.data.exception.DataAccessException;
import java.util.List;

public interface LinkedOrganizationDAO {
    boolean save(LinkedOrganizationDTO organization) throws DataAccessException;
    boolean update(LinkedOrganizationDTO organization) throws DataAccessException;
    LinkedOrganizationDTO getById(int id) throws DataAccessException;
    List<LinkedOrganizationDTO> getAll() throws DataAccessException;
}
