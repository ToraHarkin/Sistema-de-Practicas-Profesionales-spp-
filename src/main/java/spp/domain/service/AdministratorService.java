/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.domain.service;

import spp.domain.dto.CoordinatorDTO;
import spp.domain.dto.ProfessorDTO;

/**
 * Service interface for Administrator operations.
 * Defines the business logic contracts for the Administrator actor.
 */
public interface AdministratorService {

    /**
     * Registers a new Coordinator in the system.
     * @param coordinator The data transfer object containing the coordinator's information.
     * @return true if the registration was successful, false otherwise.
     */
    boolean registerCoordinator(CoordinatorDTO coordinator);

    /**
     * Disables an existing Coordinator in the system so they cannot log in.
     * @param personalNumber The unique personal number of the coordinator.
     * @return true if the deactivation was successful, false otherwise.
     */
    boolean disableCoordinator(String personalNumber);

    /**
     * Registers a new Professor in the system.
     * @param professor The data transfer object containing the professor's information.
     * @return true if the registration was successful, false otherwise.
     */
    boolean registerProfessor(ProfessorDTO professor);

    /**
     * Disables an existing Professor in the system.
     * @param personalNumber The unique personal number of the professor.
     * @return true if the deactivation was successful, false otherwise.
     */
    boolean disableProfessor(String personalNumber);
}