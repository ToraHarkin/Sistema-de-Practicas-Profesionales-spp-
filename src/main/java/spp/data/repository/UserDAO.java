package spp.data.repository;


import spp.domain.dto.UserDTO;
import spp.data.exception.PersistenceException;


public interface UserDAO {
    boolean save(UserDTO user) throws PersistenceException;
    UserDTO getByAccount(String account) throws PersistenceException;
    boolean updateStatus(int userId, String status) throws PersistenceException;
}