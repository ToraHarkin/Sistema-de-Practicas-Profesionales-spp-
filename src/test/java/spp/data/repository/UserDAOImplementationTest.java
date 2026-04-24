package spp.data.repository;


import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import spp.data.connection.ConnectionPool;
import spp.data.repository.implementation.UserDAOImplementation;
import spp.data.exception.PersistenceException;
import spp.data.exception.ConfigurationException;
import spp.domain.dto.UserDTO;
import spp.domain.enums.StatusUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.logging.Level;


public class UserDAOImplementationTest {
    private static final Logger LOGGER = Logger.getLogger(
            UserDAOImplementationTest.class.getName()
        );
    
    /**
     * CONFIG USER DATA:
     * The user data must remain the same for each test
     */
    private static final String TEST_ACCOUNT = "zS25023072";
    private static final String TEST_PASSWORD = "JLO_3315$";
    private static final StatusUser TEST_STATUS = StatusUser.ACTIVO;
    
    private UserDAOImplementation userDAO;
    
    /**
     * You can call createAuxiliaryUser to use an extra auxiliary user
     * if you need it in your tests (for example, to check if it exists).
     * Important: the SET parameters correspond to CONFIG USER DATA,
     *            THEY SHOULD NOT BE MODIFIED
     */
    private void createAuxiliaryUser() throws PersistenceException{
        UserDTO auxiliaryUser = new UserDTO();
        auxiliaryUser.setAccount(TEST_ACCOUNT);
        auxiliaryUser.setPassword(TEST_PASSWORD);
        auxiliaryUser.setStatus(TEST_STATUS);
        
        userDAO.save(auxiliaryUser);
    }

    
    private void cleanData(String account){
        try (Connection connection = ConnectionPool
                .getInstanceConectionPool().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                       "DELETE FROM usuario WHERE cuenta = ?")) {
            preparedStatement.setString(1,account);
            preparedStatement.executeUpdate();
        } catch (ConfigurationException e){
            LOGGER.log(Level.SEVERE, "property configuration error when clearing test data", e);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Error connecting to the database", e);
        }   
    } 
    
    @BeforeEach
    void setUp() {
        userDAO = new UserDAOImplementation();
    }
    
    @AfterEach
    void takeOutTrash() {
        
        //The default account must be changed if TEST_ACCOUNT changes.
        cleanData(TEST_ACCOUNT); 
    }
    
    @Test
    void Should_RegisterUser_When_EnteringUserData() throws PersistenceException{
        UserDTO testUser = new UserDTO();
        testUser.setAccount(TEST_ACCOUNT);
        testUser.setPassword(TEST_PASSWORD);
        testUser.setStatus(TEST_STATUS);
        
        boolean resultTest = userDAO.save(testUser);
        
        assertTrue(resultTest);
    }
    
    @Test
    void Should_ThrowPersistenceException_When_TryRegisterUserThatAlreadyExists() {
        createAuxiliaryUser();

        UserDTO testUser = new UserDTO();
        testUser.setAccount(TEST_ACCOUNT);
        testUser.setPassword(TEST_PASSWORD);
        testUser.setStatus(TEST_STATUS);
        
        assertThrows(PersistenceException.class, () -> userDAO.save(testUser));       
    }
    
    @Test
    void Should_ReturnUserDTO_When_AccountExists() throws PersistenceException {
        createAuxiliaryUser();
        
        UserDTO testExpectedUser = new UserDTO();
        testExpectedUser.setAccount(TEST_ACCOUNT);
        testExpectedUser.setPassword(TEST_PASSWORD);
        testExpectedUser.setStatus(TEST_STATUS);
        
        //obtains the account of the user registered in createAuxiliaryUser()
        UserDTO userObtained = userDAO.getByAccount(TEST_ACCOUNT);
        
        assertEquals(testExpectedUser, userObtained);
    }
    
    @Test
    void Should_ReturnNull_When_AccountDoesNotExist() throws PersistenceException {
        
        //user account that does not exist
        UserDTO testUser = userDAO.getByAccount("zS21458893");
        
        assertNull(testUser);
    }
    
    @Test
    void Should_UpdateUserStatus_When_UserStatusChangesUsingTheirID() throws PersistenceException {
        createAuxiliaryUser();
        
        UserDTO testUser = userDAO.getByAccount(TEST_ACCOUNT);
        boolean resultTest = userDAO.updateStatus(testUser.getId(), StatusUser.INACTIVO.getDatabaseValue());
        
        assertTrue(resultTest);
        
    }
    
    @Test 
    void Should_ReturnFalse_When_TryUpdateUserStatusAndUserDoesNotExist() throws PersistenceException{
        int nonExistentId = 999;
        
        boolean resultTest = userDAO.updateStatus(nonExistentId, StatusUser.INACTIVO.getDatabaseValue());
        
        assertFalse(resultTest);
    }
}
