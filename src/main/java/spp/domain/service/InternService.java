/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.domain.service;

import spp.domain.dto.ReportDTO;

/**
 * Service interface for Intern operations.
 * Defines the business logic contracts for the Intern actor.
 */
public interface InternService {

    /**
     * Submits a monthly or partial report for the intern's project.
     * @param report The data transfer object containing the report details.
     * @return true if the report was successfully submitted, false otherwise.
     */
    boolean submitReport(ReportDTO report);

    /**
     * Submits the intern's self-evaluation.
     * @param enrollment The unique enrollment ID of the intern.
     * @param score The calculated score of the self-evaluation.
     * @return true if the evaluation was saved, false otherwise.
     */
    boolean submitSelfEvaluation(String enrollment, int score);
    
    /**
     * Delivers the required initial documents (schedule, acceptance letter).
     * @param enrollment The unique enrollment ID of the intern.
     * @param documentPath The path where the digitized document is stored.
     * @return true if the document was registered, false otherwise.
     */
    boolean deliverDocument(String enrollment, String documentPath);
}