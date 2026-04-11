/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.domain.service;

import spp.domain.dto.LinkedOrganizationDTO;
import spp.domain.dto.OrganizationAddressDTO;
import spp.domain.exception.DuplicateEntityException;
import spp.domain.exception.IncompleteDataException;
import spp.domain.exception.EntityNotFoundException;
import spp.data.exception.DataAccessException;
import java.util.List;

public interface OrganizationService {
    void registerOrganization(LinkedOrganizationDTO organization, OrganizationAddressDTO address) 
        throws DuplicateEntityException, IncompleteDataException, DataAccessException;
        
    List<LinkedOrganizationDTO> getAllOrganizations() 
        throws DataAccessException;
        
    void updateOrganizationContact(int organizationId, String phone, String email) 
        throws IncompleteDataException, EntityNotFoundException, DataAccessException;
}