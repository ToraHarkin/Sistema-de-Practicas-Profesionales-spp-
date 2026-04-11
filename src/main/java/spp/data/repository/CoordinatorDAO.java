/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.data.repository;

import spp.domain.dto.CoordinatorDTO;
import spp.data.exception.DataAccessException;

public interface CoordinatorDAO {
    boolean save(CoordinatorDTO coordinator) throws DataAccessException;
    CoordinatorDTO getByPersonalNumber(String personalNumber) throws DataAccessException;
}