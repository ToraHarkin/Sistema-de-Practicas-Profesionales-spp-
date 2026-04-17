/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.domain.service;

import spp.domain.dto.SchoolPeriodDTO;
import spp.domain.exception.DuplicateEntityException;
import spp.domain.exception.IncompleteDataException;
import spp.data.exception.PersistenceException;
import java.util.List;

public interface SchoolPeriodService {
    void startNewPeriod(SchoolPeriodDTO period) 
        throws DuplicateEntityException, IncompleteDataException, PersistenceException;
        
    SchoolPeriodDTO getCurrentPeriod() 
        throws PersistenceException;
        
    List<SchoolPeriodDTO> getPeriodHistory() 
        throws PersistenceException;
}