package spp.data.repository;


import spp.domain.dto.UserDTO;
import spp.data.exception.DataAccessException;


public interface UserDAO {
    boolean save(UserDTO user) throws DataAccessException;
    UserDTO getByAccount(String account) throws DataAccessException;
    boolean updateStatus(int userId, String status) throws DataAccessException;
}