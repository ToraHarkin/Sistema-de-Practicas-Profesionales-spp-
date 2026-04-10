/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.domain.exception;

public class EntityNotFoundException extends BusinessException {
    public EntityNotFoundException(String entityName) {
        super("No se pudo encontrar: " + entityName);
    }
}