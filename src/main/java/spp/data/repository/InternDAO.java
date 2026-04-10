/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.data.repository;

import spp.domain.dto.InternDTO;
import java.util.List;

public interface InternDAO {
    boolean save(InternDTO intern);
    InternDTO getByEnrollment(String enrollment); // Basado en columna 'matricula' [cite: 202]
    List<InternDTO> getAll();
}