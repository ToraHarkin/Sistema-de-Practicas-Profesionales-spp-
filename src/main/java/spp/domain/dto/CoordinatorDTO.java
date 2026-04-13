package spp.domain.dto;

public class CoordinatorDTO {
    private int id;
    private String personalNumber;
    private String name;
    private String paternalSurname;
    private String maternalSurname;
    private int monthsOfService;
    private int userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPaternalSurname() {
        return paternalSurname;
    }

    public void setPaternalSurname(String paternalSurname) {
        this.paternalSurname = paternalSurname;
    }

    public String getMaternalSurname() {
        return maternalSurname;
    }

    public void setMaternalSurname(String maternalSurname) {
        this.maternalSurname = maternalSurname;
    }

    public int getMonthsOfService() {
        return monthsOfService;
    }

    public void setMonthsOfService(int monthsOfService) {
        this.monthsOfService = monthsOfService;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    
}