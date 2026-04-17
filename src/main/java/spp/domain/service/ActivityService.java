/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.domain.service;

import spp.domain.dto.ActivityDTO;
import spp.domain.dto.AssignedActivityDTO;
import spp.domain.exception.IncompleteDataException;
import spp.domain.exception.EntityNotFoundException;
import spp.data.exception.PersistenceException;

public interface ActivityService {
    void createActivity(ActivityDTO activity) 
        throws IncompleteDataException, PersistenceException;
        
    void deliverActivity(AssignedActivityDTO delivery) 
        throws IncompleteDataException, EntityNotFoundException, PersistenceException;
        
    void gradeActivity(int assignmentId, double grade) 
        throws IncompleteDataException, EntityNotFoundException, PersistenceException;
}