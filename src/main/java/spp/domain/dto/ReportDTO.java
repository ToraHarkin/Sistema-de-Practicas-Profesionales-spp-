package spp.domain.dto;

import java.sql.Timestamp;

public class ReportDTO {
    private int id;
    private String type;
    private Timestamp deliveryDate;
    private String nrc;
    private String coveredPeriod;
    private int coveredHours;
    private String description;
    private int internId;
    private int schoolPeriodId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Timestamp getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Timestamp deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getNrc() {
        return nrc;
    }

    public void setNrc(String nrc) {
        this.nrc = nrc;
    }

    public String getCoveredPeriod() {
        return coveredPeriod;
    }

    public void setCoveredPeriod(String coveredPeriod) {
        this.coveredPeriod = coveredPeriod;
    }

    public int getCoveredHours() {
        return coveredHours;
    }

    public void setCoveredHours(int coveredHours) {
        this.coveredHours = coveredHours;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getInternId() {
        return internId;
    }

    public void setInternId(int internId) {
        this.internId = internId;
    }

    public int getSchoolPeriodId() {
        return schoolPeriodId;
    }

    public void setSchoolPeriodId(int schoolPeriodId) {
        this.schoolPeriodId = schoolPeriodId;
    }

}