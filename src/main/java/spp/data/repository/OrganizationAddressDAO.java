/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.data.repository;

import spp.domain.dto.OrganizationAddressDTO;

public interface OrganizationAddressDAO {
    boolean save(OrganizationAddressDTO address);
    OrganizationAddressDTO getByOrganizationId(int organizationId);
}
