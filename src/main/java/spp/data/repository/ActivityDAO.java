/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.data.repository;

import spp.domain.dto.ActivityDTO;
import spp.data.exception.DataAccessException;
import java.util.List;

public interface ActivityDAO {
    boolean save(ActivityDTO activity) throws DataAccessException;
    List<ActivityDTO> getByProfessorId(int professorId) throws DataAccessException;
}