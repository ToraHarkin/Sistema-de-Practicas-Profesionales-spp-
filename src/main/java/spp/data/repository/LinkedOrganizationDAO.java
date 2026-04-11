/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.data.repository;

import spp.domain.dto.LinkedOrganizationDTO;
import spp.data.exception.DataAccessException;

public interface LinkedOrganizationDAO {
    boolean save(LinkedOrganizationDTO organization) throws DataAccessException;
    LinkedOrganizationDTO getById(int id) throws DataAccessException;
}
