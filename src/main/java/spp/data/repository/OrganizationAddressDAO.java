/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.data.repository;

import spp.domain.dto.OrganizationAddressDTO;
import spp.data.exception.DataAccessException;

public interface OrganizationAddressDAO {
    boolean save(OrganizationAddressDTO address) throws DataAccessException;
    OrganizationAddressDTO getByOrganizationId(int organizationId) throws DataAccessException;
}
