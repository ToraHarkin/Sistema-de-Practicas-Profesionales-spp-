/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.domain.service.implementation;

import spp.domain.service.CoordinatorService;
import spp.domain.dto.InternDTO;

/**
 * Implementation of the CoordinatorService interface.
 * Handles the business logic and rules for Coordinator operations.
 */
public class CoordinatorServiceImplementation implements CoordinatorService {

    private static final int MAX_PROJECT_CAPACITY = 3;

    public CoordinatorServiceImplementation() {
    }

    /**
     * Registers a new intern in the system applying business rules.
     * * @param intern The data transfer object containing the intern's information.
     * @return true if the registration was successful, false otherwise.
     */
    @Override
    public boolean registerIntern(InternDTO intern) {
        boolean isRegistered = false;

        try {
            if (intern == null || intern.getEnrollment() == null || intern.getEnrollment().isEmpty()) {
                System.out.println("WARN: Intern data or enrollment cannot be empty.");
                return false;
            }
            
            // Simulating successful save for now
            isRegistered = true; 
            System.out.println("INFO: Intern registered successfully with enrollment: " + intern.getEnrollment());

        } catch (Exception e) {
            System.out.println("ERROR: Could not register the intern. Details: " + e.getMessage());
        } finally {
            System.out.println("DEBUG: registerIntern process finished.");
        }

        return isRegistered;
    }

    /**
     * Assigns an intern to a specific project validating capacity.
     * * @param enrollment The unique enrollment ID of the intern.
     * @param projectId The unique ID of the project.
     * @return true if the assignment was successful, false otherwise.
     */
    @Override
    public boolean assignProject(String enrollment, int projectId) {
        boolean isAssigned = false;

        try {
            if (enrollment == null || enrollment.isEmpty() || projectId <= 0) {
                System.out.println("WARN: Invalid parameters for project assignment.");
                return false;
            }

            // Logic to check project capacity and intern status would go here
            // using the Data layer repositories.

            System.out.println("INFO: Intern " + enrollment + " successfully assigned to project " + projectId);
            isAssigned = true;

        } catch (Exception e) {
            System.out.println("ERROR: Could not assign project. Details: " + e.getMessage());
        } finally {
            System.out.println("DEBUG: assignProject process finished.");
        }

        return isAssigned;
    }
}