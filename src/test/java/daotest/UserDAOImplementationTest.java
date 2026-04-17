package daotest;


import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import spp.data.repository.implementation.UserDAOImplementation;
import spp.domain.dto.UserDTO;


public class UserDAOImplementationTest {
   @Test
    void save() {
        UserDAOImplementation userDAO = new  UserDAOImplementation();
       
        UserDTO user = new UserDTO();
        user.setAccount("AAAA");
        user.setPassword("1234");
        user.setStatus("Activo");
        
        boolean result = userDAO.save(user);
        assertTrue(result);
    }
   
   @Test
   void getByAccount() {
        UserDAOImplementation userDAO = new  UserDAOImplementation();
        
        UserDTO user = new UserDTO();
        user.setAccount("AAAB");
        user.setPassword("1234");
        user.setStatus("Activo");
        
        userDAO.save(user);
        UserDTO retrievedUser = userDAO.getByAccount("AAAB");
        
        assertNotNull(retrievedUser);
        assertEquals("AAAB", retrievedUser.getAccount());     
   }
   
   @Test
   void updateStatus(){
       UserDAOImplementation userDAO = new  UserDAOImplementation();
        
       UserDTO user = new UserDTO();
       user.setAccount("AAAC");
       user.setPassword("1234");
       user.setStatus("Activo");
        
       userDAO.save(user);
       UserDTO retrievedUser = userDAO.getByAccount("AAAC");
       
       boolean update = userDAO.updateStatus(retrievedUser.getId(), "Inactivo");
       assertTrue(update);
       
       UserDTO retrievedUserUpdate = userDAO.getByAccount("AAAC");
       assertEquals("Inactivo", retrievedUserUpdate.getStatus());
   }
}
