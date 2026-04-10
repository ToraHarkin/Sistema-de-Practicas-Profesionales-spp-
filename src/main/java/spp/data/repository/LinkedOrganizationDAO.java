/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.data.repository;

import spp.domain.dto.LinkedOrganizationDTO;

public interface LinkedOrganizationDAO {
    boolean save(LinkedOrganizationDTO organization);
    LinkedOrganizationDTO getById(int id);
}
