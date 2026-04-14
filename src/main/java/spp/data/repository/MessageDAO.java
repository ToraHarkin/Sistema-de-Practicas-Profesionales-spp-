package spp.data.repository;


import spp.domain.dto.MessageDTO;
import spp.data.exception.DataAccessException;
import java.util.List;



public interface MessageDAO {
    boolean save(MessageDTO message) throws DataAccessException;
    boolean update(MessageDTO message) throws DataAccessException;
    MessageDTO getById(int id) throws DataAccessException;
    List<MessageDTO> getAll() throws DataAccessException;
}