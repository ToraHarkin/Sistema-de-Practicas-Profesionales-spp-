package spp.domain.enums;

import spp.domain.exception.InvalidEnumValueException;


public enum StatusMessageBox {
    ENVIADO("Enviado"),
    BORRADOR("Borrador");
    
    private final String databaseValue;
    
    StatusMessageBox(String databaseValue) {
        this.databaseValue = databaseValue;
    }
    
    public String getDatabaseValue() {
        return databaseValue;
    }
    
    public static StatusMessageBox fromDatabaseValue(String databaseValue ){
        for(StatusMessageBox status : StatusMessageBox.values()) {
            if(status.databaseValue.equalsIgnoreCase(databaseValue)) {
                return status;
            }
        }
        
        throw new InvalidEnumValueException("Invalid value for Gender:" + databaseValue);
    }
}
