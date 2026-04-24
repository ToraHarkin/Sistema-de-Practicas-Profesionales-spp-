//package daotest;


//import org.junit.jupiter.api.*;
//import static org.junit.jupiter.api.Assertions.*;
//import spp.data.repository.implementation.UserDAOImplementation;
//import spp.domain.dto.UserDTO;
//import spp.data.exception.PersistenceException;


//public class UserDAOImplementationTest {
//   @Test
//    void shouldSaveUserSuccessfully() {
//        UserDAOImplementation userDAO = new  UserDAOImplementation();
       
//        UserDTO user = new UserDTO();
//        user.setAccount("zS202021");
//        user.setPassword("12345");
//        user.setStatus("Activo");
        
//        boolean result = userDAO.save(user);
//        assertTrue(result);
        
//        UserDTO userSaved = userDAO.getByAccount("zS24202021");
//        assertNotNull(userSaved);
//    }
    
//    @Test
//    void saveUserThrowsExceptionWhenDataInvalid() {
//        UserDAOImplementation userDAO = new  UserDAOImplementation();
        
//        UserDTO user = new UserDTO();
//        user.setAccount(null);
//        user.setPassword("12345");
//        user.setStatus("Activo");
        
//        PersistenceException ex = assertThrows(
//            PersistenceException.class,
//            () -> userDAO.save(user)
//        );
//    }
   
//   @Test
//   void shouldReturnUserByAccount() {
//        UserDAOImplementation userDAO = new  UserDAOImplementation();
        
//        UserDTO user = new UserDTO();
//        user.setAccount("zS24013272");
//        user.setPassword("1234");
//        user.setStatus("Activo");
        
//        userDAO.save(user);
//        UserDTO retrievedUser = userDAO.getByAccount("zS24013272");
        
//        assertNotNull(retrievedUser);
//        assertEquals("zS24013272", retrievedUser.getAccount());     
//   }
   
//   @Test
//   void shouldUpdateUserStatus() {
//       UserDAOImplementation userDAO = new  UserDAOImplementation();
        
//       UserDTO user = new UserDTO();
//       user.setAccount("1234567891");
//       user.setPassword("12345");
//       user.setStatus("Activo");
        
//       userDAO.save(user);
//       UserDTO retrievedUser = userDAO.getByAccount("1234567891");
       
//       boolean update = userDAO.updateStatus(retrievedUser.getId(), "Inactivo");
//       assertTrue(update);
       
//       UserDTO retrievedUserUpdate = userDAO.getByAccount("1234567891");
//       assertEquals("Inactivo", retrievedUserUpdate.getStatus());
//   }
//}
