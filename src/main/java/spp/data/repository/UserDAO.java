/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.data.repository;

import spp.domain.dto.UserDTO;

public interface UserDAO {
    boolean save(UserDTO user);
    UserDTO getByUsername(String username); // Basado en columna 'cuenta' [cite: 202]
    boolean updateStatus(int id, String status);
}