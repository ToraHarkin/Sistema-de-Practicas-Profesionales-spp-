/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.data.repository;

import spp.domain.dto.SupportDocumentDTO;
import spp.data.exception.DataAccessException;
import java.util.List;

public interface SupportDocumentDAO {
    boolean save(SupportDocumentDTO document) throws DataAccessException;
    List<SupportDocumentDTO> getByInternId(int internId) throws DataAccessException;
    boolean updateGrade(int documentId, double grade) throws DataAccessException;
}
