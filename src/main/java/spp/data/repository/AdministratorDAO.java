/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author ADMIN
 */
package spp.data.repository;

import spp.domain.dto.AdministratorDTO;

public interface AdministratorDAO {
    boolean save(AdministratorDTO administrator);
    AdministratorDTO getByUsername(String username); // Columna cuenta_usuario [cite: 322]
}