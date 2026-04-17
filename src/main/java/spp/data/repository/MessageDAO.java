package spp.data.repository;


import spp.domain.dto.MessageDTO;
import spp.data.exception.PersistenceException;
import java.util.List;



public interface MessageDAO {
    boolean save(MessageDTO message) throws PersistenceException;
    boolean update(MessageDTO message) throws PersistenceException;
    MessageDTO getById(int id) throws PersistenceException;
    List<MessageDTO> getAll() throws PersistenceException;
}