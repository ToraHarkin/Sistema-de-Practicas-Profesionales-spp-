/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.domain.service;

import spp.domain.dto.InternDTO;
import spp.domain.dto.UserDTO;
import spp.domain.exception.DuplicateEntityException;
import spp.domain.exception.IncompleteDataException;
import spp.domain.exception.EntityNotFoundException;
import spp.data.exception.DataAccessException;

public interface InternService {
    
    void registerIntern(InternDTO intern, UserDTO user) 
        throws DuplicateEntityException, IncompleteDataException, DataAccessException;
        
    InternDTO getInternProfile(String enrollment) 
        throws EntityNotFoundException, DataAccessException;
}