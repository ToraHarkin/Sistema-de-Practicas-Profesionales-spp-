/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.domain.service;

import spp.domain.dto.ActivityDTO;
import spp.domain.dto.AssignedActivityDTO;

public interface ActivityService {
    void createActivity(ActivityDTO activity);
    void deliverActivity(AssignedActivityDTO delivery);
    void gradeActivity(int assignmentId, double grade);
}
