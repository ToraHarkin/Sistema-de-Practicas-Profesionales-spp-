/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.domain.service;

import spp.domain.dto.ProjectDTO;
import spp.domain.dto.ProjectResponsibleDTO;
import spp.domain.exception.DuplicateEntityException;
import spp.domain.exception.IncompleteDataException;
import spp.data.exception.DataAccessException;
import java.util.List;

public interface ProjectService {
    
    void registerProject(ProjectDTO project, ProjectResponsibleDTO responsible) 
        throws DuplicateEntityException, IncompleteDataException, DataAccessException;
        
    List<ProjectDTO> getAvailableProjects() 
        throws DataAccessException;
}