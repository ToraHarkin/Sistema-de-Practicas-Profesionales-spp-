/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.domain.dto;

import java.time.LocalDateTime;

public class ProjectActivityDTO {
    private int id;
    private String name;
    private String description;
    private double plannedTime;
    private double realTime;
    private double openingDate; // Hace match con fecha_apertura DECIMAL(4,1)
    private double closingDate; // Hace match con fecha_cierre DECIMAL(4,1)
    private int projectId;

    public ProjectActivityDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPlannedTime() {
        return plannedTime;
    }

    public void setPlannedTime(double plannedTime) {
        this.plannedTime = plannedTime;
    }

    public double getRealTime() {
        return realTime;
    }

    public void setRealTime(double realTime) {
        this.realTime = realTime;
    }

    public double getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(double openingDate) {
        this.openingDate = openingDate;
    }

    public double getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(double closingDate) {
        this.closingDate = closingDate;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    
}