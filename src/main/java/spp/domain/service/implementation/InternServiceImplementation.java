/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 
package spp.domain.service.implementation;

import spp.domain.service.InternService;
import spp.domain.dto.ReportDTO;

/**
 * Implementation of the InternService interface.
 * Handles the business logic and rules for Intern operations.
 
public class InternServiceImplementation implements InternService {

    private static final int MIN_PASSING_SCORE = 70;

    public InternServiceImplementation() {
    }

    @Override
    public boolean submitReport(ReportDTO report) {
        boolean isSubmitted = false;

        try {
            if (report == null || report.getDescription() == null || report.getDescription().isEmpty()) {
                System.out.println("WARN: Report data cannot be empty.");
                return false;
            }
            
            System.out.println("INFO: Report submitted successfully for period: " + report.getCoveredPeriod());
            isSubmitted = true;

        } catch (Exception e) {
            System.out.println("ERROR: Could not submit the report. Details: " + e.getMessage());
        } finally {
            System.out.println("DEBUG: submitReport process finished.");
        }

        return isSubmitted;
    }

    @Override
    public boolean submitSelfEvaluation(String enrollment, int score) {
        boolean isEvaluated = false;

        try {
            if (enrollment == null || enrollment.isEmpty() || score < 0 || score > 100) {
                System.out.println("WARN: Invalid data for self-evaluation.");
                return false;
            }

            System.out.println("INFO: Self-evaluation submitted for intern: " + enrollment + " with score: " + score);
            isEvaluated = true;

        } catch (Exception e) {
            System.out.println("ERROR: Could not submit self-evaluation. Details: " + e.getMessage());
        } finally {
            System.out.println("DEBUG: submitSelfEvaluation process finished.");
        }

        return isEvaluated;
    }

    @Override
    public boolean deliverDocument(String enrollment, String documentPath) {
        boolean isDelivered = false;

        try {
            if (enrollment == null || documentPath == null || documentPath.isEmpty()) {
                System.out.println("WARN: Invalid document or enrollment data.");
                return false;
            }

            System.out.println("INFO: Document registered at path: " + documentPath);
            isDelivered = true;

        } catch (Exception e) {
            System.out.println("ERROR: Could not deliver document. Details: " + e.getMessage());
        } finally {
            System.out.println("DEBUG: deliverDocument process finished.");
        }

        return isDelivered;
    }
}
*/