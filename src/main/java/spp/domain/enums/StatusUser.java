package spp.domain.enums;


import spp.domain.exception.InvalidEnumValueException;


public enum StatusUser {
    ACTIVO("Activo"),
    INACTIVO("Inactivo");
    
    private final String databaseValue;

    private StatusUser(String databaseValue) {
        this.databaseValue = databaseValue;
    }
    
     public String getDatabaseValue() {
        return databaseValue;
    }
     
    public static StatusUser fromDatabaseValue(String databaseValue ){
        for(StatusUser status : StatusUser.values()) {
            if(status.databaseValue.equalsIgnoreCase(databaseValue)) {
                return status;
            }
        }
     
        throw new InvalidEnumValueException("Invalid value for user status:" + databaseValue);
    }
}
