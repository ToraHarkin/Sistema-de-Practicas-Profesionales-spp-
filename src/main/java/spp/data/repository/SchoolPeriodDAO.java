/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package spp.data.repository;

import spp.domain.dto.SchoolPeriodDTO;
import spp.data.exception.DataAccessException;

public interface SchoolPeriodDAO {
    boolean save(SchoolPeriodDTO period) throws DataAccessException;
    SchoolPeriodDTO getById(int id) throws DataAccessException;
}
