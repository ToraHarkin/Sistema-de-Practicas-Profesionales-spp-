/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.domain.service;

import spp.domain.dto.SchoolPeriodDTO;
import spp.domain.exception.DuplicateEntityException;
import spp.domain.exception.IncompleteDataException;
import spp.data.exception.DataAccessException;
import java.util.List;

public interface SchoolPeriodService {
    void startNewPeriod(SchoolPeriodDTO period) 
        throws DuplicateEntityException, IncompleteDataException, DataAccessException;
        
    SchoolPeriodDTO getCurrentPeriod() 
        throws DataAccessException;
        
    List<SchoolPeriodDTO> getPeriodHistory() 
        throws DataAccessException;
}