package spp.domain.service;


import spp.data.exception.PersistenceException;
import spp.data.repository.AdministratorDAO;


public class AppService {
    private final AdministratorDAO administratorDAO;
    
    public AppService(AdministratorDAO administratorDAO) {
        this.administratorDAO = administratorDAO;
    }
    
    public String determinateInitialView() throws PersistenceException{
        return administratorDAO.existsAdministrator() ? "login" : "welcome";
    }
    
}
