/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.data.repository;

import spp.domain.dto.SupportDocumentDTO;
import java.util.List;

public interface SupportDocumentDAO {
    boolean save(SupportDocumentDTO document);
    List<SupportDocumentDTO> getByInternId(int internId);
    boolean updateGrade(int documentId, double grade); // Columna calificacion [cite: 246]
}
