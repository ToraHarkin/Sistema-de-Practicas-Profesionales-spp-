/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.domain.service;

import spp.domain.dto.LinkedOrganizationDTO;
import spp.domain.dto.OrganizationAddressDTO;
import java.util.List;

public interface OrganizationService {
    void registerOrganization(LinkedOrganizationDTO organization, OrganizationAddressDTO address);
    List<LinkedOrganizationDTO> getAllOrganizations();
    void updateOrganizationContact(int organizationId, String phone, String email);
}