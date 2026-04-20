package spp.domain.enums;


import spp.domain.exception.InvalidEnumValueException;


public enum Gender {
    MASCULINO("Masculino"),
    FEMENINO("Femenino");
    
    private final String databaseValue;
    
    Gender(String databaseValue) {
        this.databaseValue = databaseValue;
    }
    
    public String getDatabaseValue() {
        return databaseValue;
    }
    
    public static Gender fromDatabaseValue(String databaseValue ){
        for(Gender gender : Gender.values()) {
            if(gender.databaseValue.equalsIgnoreCase(databaseValue)) {
                return gender;
            }
        }
        
        throw new InvalidEnumValueException("Invalid value for Gender:" + databaseValue);
    }
}
