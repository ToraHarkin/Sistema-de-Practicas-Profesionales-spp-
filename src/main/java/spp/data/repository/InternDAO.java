/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.data.repository;

import spp.domain.dto.InternDTO;
import spp.data.exception.DataAccessException;
import java.util.List;

public interface InternDAO {
    boolean save(InternDTO intern) throws DataAccessException;
    InternDTO getByEnrollment(String enrollment) throws DataAccessException;
    List<InternDTO> getAll() throws DataAccessException;
}