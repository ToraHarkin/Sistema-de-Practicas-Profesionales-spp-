/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.data.repository;

import spp.domain.dto.UserDTO;
import spp.data.exception.DataAccessException;

public interface UserDAO {
    boolean save(UserDTO user) throws DataAccessException;
    UserDTO getByUsername(String username) throws DataAccessException;
    boolean updateStatus(int id, String status) throws DataAccessException;
}