/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.data.repository;

import spp.domain.dto.ProjectResponsibleDTO;
import spp.data.exception.DataAccessException;

public interface ProjectResponsibleDAO {
    boolean save(ProjectResponsibleDTO responsible) throws DataAccessException;
    ProjectResponsibleDTO getByProjectId(int projectId) throws DataAccessException;
}