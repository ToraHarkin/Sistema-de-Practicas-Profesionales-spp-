package spp.domain.enums;

import spp.domain.exception.InvalidEnumValueException;


public enum RoleMessageBox {
    EMISOR("Emisor"),
    RECEPTOR("Receptor");
    
    private final String databaseValue;
    
    RoleMessageBox(String databaseValue) {
        this.databaseValue = databaseValue;
    }
    
    public String getDatabaseValue() {
        return databaseValue;
    }
    
    public static RoleMessageBox fromDatabaseValue(String databaseValue ){
        for(RoleMessageBox role : RoleMessageBox.values()) {
            if(role.databaseValue.equalsIgnoreCase(databaseValue)) {
                return role;
            }
        }
        
        throw new InvalidEnumValueException("Invalid value for role email:" + databaseValue);
    }
}
