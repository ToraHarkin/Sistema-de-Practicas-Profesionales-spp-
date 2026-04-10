/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.domain.service;

import spp.domain.dto.SelfEvaluationDTO;
import spp.domain.dto.SupportDocumentDTO;

public interface EvaluationService {
    void registerSelfEvaluation(SelfEvaluationDTO selfEvaluation);
    void gradeSupportDocument(int documentId, double grade, String observations);
    double calculateFinalAverage(int internId); // Para el expediente
}