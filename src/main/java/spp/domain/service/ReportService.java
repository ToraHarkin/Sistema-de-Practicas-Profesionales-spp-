/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.domain.service;

import spp.domain.dto.ReportDTO;
import spp.domain.exception.IncompleteDataException;
import spp.domain.exception.FileStorageException;
import spp.data.exception.DataAccessException;
import java.util.List;

public interface ReportService {
    void uploadReport(ReportDTO report) 
        throws IncompleteDataException, FileStorageException, DataAccessException;
        
    List<ReportDTO> getInternReports(int internId) 
        throws DataAccessException;
        
    void validateReport(int reportId, String observations) 
        throws IncompleteDataException, DataAccessException;
}