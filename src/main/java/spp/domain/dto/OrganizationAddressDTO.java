package spp.domain.dto;

public class OrganizationAddressDTO {
    private int id;
    private String street;
    private String externalNumber;
    private String internalNumber;
    private String neighborhood;
    private String zipCode;
    private String city;
    private String country;
    private int linkedOrganizationId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getExternalNumber() {
        return externalNumber;
    }

    public void setExternalNumber(String externalNumber) {
        this.externalNumber = externalNumber;
    }

    public String getInternalNumber() {
        return internalNumber;
    }

    public void setInternalNumber(String internalNumber) {
        this.internalNumber = internalNumber;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getLinkedOrganizationId() {
        return linkedOrganizationId;
    }

    public void setLinkedOrganizationId(int linkedOrganizationId) {
        this.linkedOrganizationId = linkedOrganizationId;
    }

    
}