/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.domain.service;

import spp.domain.dto.SchoolPeriodDTO;
import java.util.List;

public interface SchoolPeriodService {
    void startNewPeriod(SchoolPeriodDTO period);
    SchoolPeriodDTO getCurrentPeriod();
    List<SchoolPeriodDTO> getPeriodHistory();
}