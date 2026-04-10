/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.domain.service;

import spp.domain.dto.ReportDTO;
import java.util.List;

public interface ReportService {
    void uploadReport(ReportDTO report);
    List<ReportDTO> getInternReports(int internId);
    void validateReport(int reportId, String observations);
}