/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package spp.data.repository;

import spp.domain.dto.AdministratorDTO;
import spp.data.exception.DataAccessException;

public interface AdministratorDAO {
    boolean save(AdministratorDTO administrator) throws DataAccessException;
    AdministratorDTO getByUsername(String username) throws DataAccessException;
}