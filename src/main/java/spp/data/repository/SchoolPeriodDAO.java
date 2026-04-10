/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ADMIN
 */
package spp.data.repository;

import spp.domain.dto.SchoolPeriodDTO;

public interface SchoolPeriodDAO {
    boolean save(SchoolPeriodDTO period);
    SchoolPeriodDTO getCurrentPeriod();
}
