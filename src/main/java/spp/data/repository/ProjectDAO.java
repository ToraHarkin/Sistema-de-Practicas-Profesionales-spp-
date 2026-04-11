/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.data.repository;

import spp.domain.dto.ProjectDTO;
import spp.data.exception.DataAccessException;
import java.util.List;

public interface ProjectDAO {
    boolean save(ProjectDTO project) throws DataAccessException;
    List<ProjectDTO> getAllAvailable() throws DataAccessException;
    ProjectDTO getById(int id) throws DataAccessException;
}