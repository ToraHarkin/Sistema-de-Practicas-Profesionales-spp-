/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.domain.service;

import spp.domain.dto.InternDTO;

/**
 * Service interface for Coordinator operations.
 * Defines the business logic contracts for the Coordinator actor.
 */
public interface CoordinatorService {

    /**
     * Registers a new intern in the system.
     * * @param intern The data transfer object containing the intern's information.
     * @return true if the registration was successful, false otherwise.
     */
    boolean registerIntern(InternDTO intern);

    /**
     * Assigns an intern to a specific project.
     * * @param enrollment The unique enrollment ID of the intern.
     * @param projectId The unique ID of the project.
     * @return true if the assignment was successful, false otherwise.
     */
    boolean assignProject(String enrollment, int projectId);
}