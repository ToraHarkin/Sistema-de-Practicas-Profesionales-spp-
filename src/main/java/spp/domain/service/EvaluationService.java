/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.domain.service;

import spp.domain.dto.SelfEvaluationDTO;
import spp.domain.exception.DuplicateEntityException;
import spp.domain.exception.IncompleteDataException;
import spp.domain.exception.EntityNotFoundException;
import spp.data.exception.PersistenceException;

public interface EvaluationService {
    void registerSelfEvaluation(SelfEvaluationDTO selfEvaluation) 
        throws DuplicateEntityException, IncompleteDataException, PersistenceException;
        
    void gradeSupportDocument(int documentId, double grade, String observations) 
        throws IncompleteDataException, EntityNotFoundException, PersistenceException;
        
    double calculateFinalAverage(int internId) 
        throws EntityNotFoundException, PersistenceException;
}