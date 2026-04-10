/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.domain.service;

import spp.domain.dto.InternDTO;
import spp.domain.dto.UserDTO;

public interface InternService {
    // Registra al usuario y al practicante como una sola acción
    void registerIntern(InternDTO intern, UserDTO user); 
    InternDTO getInternProfile(String enrollment);
}