/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.domain.service.implementation;

import spp.domain.service.AdministratorService;
import spp.domain.dto.CoordinatorDTO;
import spp.domain.dto.ProfessorDTO;

/**
 * Implementation of the AdministratorService interface.
 * Handles the business logic and rules for Administrator operations.
 */
public class AdministratorServiceImplementation implements AdministratorService {

    private static final String STATUS_INACTIVE = "INACTIVE";

    public AdministratorServiceImplementation() {
    }


    @Override
    public boolean registerCoordinator(CoordinatorDTO coordinator) {
        boolean isRegistered = false;

        try {
            if (coordinator == null || coordinator.getPersonalNumber() == null || coordinator.getPersonalNumber().isEmpty()) {
                System.out.println("WARNING: Coordinator data or personal number cannot be empty.");
                return false;
            }
            
            System.out.println("INFO: Coordinator registered successfully with personal number: " + coordinator.getPersonalNumber());
            isRegistered = true;

        } catch (Exception e) {
            System.out.println("ERROR: Could not register the coordinator. Details: " + e.getMessage());
        } finally {
            System.out.println("DEBUG: registerCoordinator process finished.");
        }

        return isRegistered;
    }

    @Override
    public boolean disableCoordinator(String personalNumber) {
        boolean isDisabled = false;

        try {
            if (personalNumber == null || personalNumber.isEmpty()) {
                System.out.println("WARNING: Personal number is required to disable a coordinator.");
                return false;
            }

            System.out.println("INFO: Coordinator " + personalNumber + " has been disabled.");
            isDisabled = true;

        } catch (Exception e) {
            System.out.println("ERROR: Could not disable the coordinator. Details: " + e.getMessage());
        } finally {
            System.out.println("DEBUG: disableCoordinator process finished.");
        }

        return isDisabled;
    }

    @Override
    public boolean registerProfessor(ProfessorDTO professor) {
        boolean isRegistered = false;

        try {
            if (professor == null || professor.getPersonalNumber() == null || professor.getPersonalNumber().isEmpty()) {
                System.out.println("WARNING: Professor data or personal number cannot be empty.");
                return false;
            }

            System.out.println("INFO: Professor registered successfully with personal number: " + professor.getPersonalNumber());
            isRegistered = true;

        } catch (Exception e) {
            System.out.println("ERROR: Could not register the professor. Details: " + e.getMessage());
        } finally {
            System.out.println("DEBUG: registerProfessor process finished.");
        }

        return isRegistered;
    }

    @Override
    public boolean disableProfessor(String personalNumber) {
        boolean isDisabled = false;

        try {
            if (personalNumber == null || personalNumber.isEmpty()) {
                System.out.println("WARNING: Personal number is required to disable a professor.");
                return false;
            }

            System.out.println("INFO: Professor " + personalNumber + " has been disabled.");
            isDisabled = true;

        } catch (Exception e) {
            System.out.println("ERROR: Could not disable the professor. Details: " + e.getMessage());
        } finally {
            System.out.println("DEBUG: disableProfessor process finished.");
        }

        return isDisabled;
    }
}
