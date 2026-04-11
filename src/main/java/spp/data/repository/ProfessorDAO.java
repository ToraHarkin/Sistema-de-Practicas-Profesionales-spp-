/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.data.repository;

import spp.domain.dto.ProfessorDTO;
import spp.data.exception.DataAccessException;

public interface ProfessorDAO {
    boolean save(ProfessorDTO professor) throws DataAccessException;
    ProfessorDTO getByPersonalNumber(String personalNumber) throws DataAccessException;
}
