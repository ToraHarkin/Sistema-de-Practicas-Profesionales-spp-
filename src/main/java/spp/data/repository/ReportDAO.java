/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.data.repository;

import spp.domain.dto.ReportDTO;
import spp.data.exception.DataAccessException;
import java.util.List;

public interface ReportDAO {
    boolean save(ReportDTO report) throws DataAccessException;
    List<ReportDTO> getByInternId(int internId) throws DataAccessException;
}