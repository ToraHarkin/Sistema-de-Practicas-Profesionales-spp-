/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.data.repository;

import spp.domain.dto.AssignedActivityDTO;
import spp.data.exception.DataAccessException;

public interface AssignedActivityDAO {
    boolean saveAssignment(AssignedActivityDTO assignment) throws DataAccessException;
    boolean updateGrade(int assignmentId, double grade) throws DataAccessException;
    AssignedActivityDTO getAssignment(int internId, int activityId) throws DataAccessException;
}