package spp.data.repository;


import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import spp.data.connection.ConnectionPool;
import spp.data.repository.implementation.AdministratorDAOImplementation;
import spp.data.repository.implementation.UserDAOImplementation;
import spp.data.exception.PersistenceException;
import spp.data.exception.ConfigurationException;
import spp.domain.dto.AdministratorDTO;
import spp.domain.dto.UserDTO;
import spp.domain.enums.StatusUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.logging.Level;



public class AdministratorDAOImplementationTest {
    private static final Logger LOGGER = Logger.getLogger(
            UserDAOImplementationTest.class.getName()
        );
    
    /**
     * CONFIG USER DATA:
     * The user data must remain the same for each test
     */
    //Admin:
    private static final String TEST_ACCOUNT = "test_admin_user";
    private static final String TEST_ACCOUNT_UPDATED = "test_admin_up";
    
    //User:
    private static final String TEST_USERPASS = "12345";
    private static final StatusUser TEST_USERSTATUS = StatusUser.ACTIVO;
    
    private AdministratorDAOImplementation administratorDAO;
    private UserDAOImplementation userTestHelperDAO;
    
    
    /**
     * You can call createAuxiliaryAdmin to use an extra auxiliary admin
     * if you need it in your tests (for example, to check if it exists).
     * Important: the SET parameters correspond to CONFIG USER DATA,
     *            THEY SHOULD NOT BE MODIFIED
     */
    private void createAuxiliaryAdmin() throws PersistenceException {
        UserDTO userCreated = userTestHelperDAO.getByAccount(TEST_ACCOUNT);
        
        AdministratorDTO testAdmin = new AdministratorDTO();
        testAdmin.setUserAccount(TEST_ACCOUNT);
        testAdmin.setUserId(userCreated.getId());
        administratorDAO.save(testAdmin);
        
    }
    
    private void cleanData() {
        try (Connection connection = ConnectionPool
                .getInstanceConectionPool().getConnection();
            PreparedStatement deleteAdmin = connection.prepareStatement(
                    "DELETE FROM administrador WHERE cuenta_usuario = ?");
            PreparedStatement deleteUser = connection.prepareStatement(
                    "DELETE FROM usuario WHERE cuenta = ?")) {

            deleteAdmin.setString(1, TEST_ACCOUNT);
            deleteAdmin.executeUpdate();
            
            deleteAdmin.setString(1, TEST_ACCOUNT_UPDATED);
            deleteAdmin.executeUpdate();

            deleteUser.setString(1, TEST_ACCOUNT);
            deleteUser.executeUpdate();

        } catch (ConfigurationException e) {
            LOGGER.log(Level.SEVERE, "property configuration error when clearing test data", e);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Error connecting to the database", e);
        }
    }
    
    
    
    @BeforeEach
    void setUp() throws PersistenceException{
        UserDTO testUser = new UserDTO();
        testUser.setAccount(TEST_ACCOUNT);
        testUser.setPassword(TEST_USERPASS);
        testUser.setStatus(TEST_USERSTATUS);
        
        userTestHelperDAO = new UserDAOImplementation();
        userTestHelperDAO.save(testUser);
        
        administratorDAO = new AdministratorDAOImplementation();
    }
    
    @AfterEach
    void takeOutTrash() {
        cleanData();
    }
    
    @Test
    void Should_ReturnAdministratorDTO_When_AccountExists() throws PersistenceException {
        createAuxiliaryAdmin();

        AdministratorDTO testExpectedAdmin = new AdministratorDTO();
        testExpectedAdmin.setUserAccount(TEST_ACCOUNT);
        testExpectedAdmin.setUserId(userTestHelperDAO.getByAccount(TEST_ACCOUNT).getId());
        AdministratorDTO adminObtained = administratorDAO.getByUserAccount(TEST_ACCOUNT);
        
        assertEquals(testExpectedAdmin,adminObtained);

    }
    
    @Test
    void Should_ReturnNull_When_AdministratorAccountDoesNotExist() throws PersistenceException {
        AdministratorDTO resultTest = administratorDAO.getByUserAccount("non_exist_admin");

        assertNull(resultTest); 
    }
    
    @Test
    void Should_ReturnTrue_When_AdministratorIsUpdated() throws PersistenceException {
        createAuxiliaryAdmin();

        AdministratorDTO adminToUpdate = administratorDAO.getByUserAccount(TEST_ACCOUNT);
        adminToUpdate.setUserAccount("test_admin_up");

        boolean resultTest = administratorDAO.update(adminToUpdate);

        assertTrue(resultTest);
    }
    
    @Test
    void Should_ReturnFalse_When_TryUpdateAdministratorThatDoesNotExist() throws PersistenceException {
        AdministratorDTO nonExistentAdmin = new AdministratorDTO();
        nonExistentAdmin.setId(999);
        nonExistentAdmin.setUserAccount("non_exist_admin");
        nonExistentAdmin.setUserId(9999);

        boolean result = administratorDAO.update(nonExistentAdmin);

        assertFalse(result);
    }
}
