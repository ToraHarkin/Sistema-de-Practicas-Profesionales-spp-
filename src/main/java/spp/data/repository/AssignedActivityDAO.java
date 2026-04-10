/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.data.repository;

import spp.domain.dto.AssignedActivityDTO;

public interface AssignedActivityDAO {
    boolean saveAssignment(AssignedActivityDTO assignment);
    boolean updateGrade(int assignmentId, double grade);
    AssignedActivityDTO getAssignment(int internId, int activityId);
}