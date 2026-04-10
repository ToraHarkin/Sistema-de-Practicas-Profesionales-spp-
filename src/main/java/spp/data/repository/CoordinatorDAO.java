/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.data.repository;

import spp.domain.dto.CoordinatorDTO;

public interface CoordinatorDAO {
    boolean save(CoordinatorDTO coordinator);
    CoordinatorDTO getByPersonalNumber(String personalNumber); // Columna numero_personal [cite: 369]
}