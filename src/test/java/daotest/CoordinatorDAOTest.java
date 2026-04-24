/** 
 * CU-A-1 (Registrar Coordinador)
 */

package daotest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import spp.domain.dto.CoordinatorDTO;
import spp.data.repository.implementation.CoordinatorDAOImplementation;
import spp.data.exception.PersistenceException;

/**
 * Unit tests for CoordinatorDAOImplementation.
 * Verifies the correct persistence of coordinator data in the system's database.
 */
public class CoordinatorDAOTest {

    private CoordinatorDAOImplementation coordinatorDAO;
    private CoordinatorDTO testCoordinator;

    /**
     * Sets up the test environment by instantiating the DAO 
     * and preparing a mock CoordinatorDTO with valid data.
     */
    @BeforeEach
    void setUp() {
        coordinatorDAO = new CoordinatorDAOImplementation();
        testCoordinator = new CoordinatorDTO();
        
        // Using the exact setter methods from CoordinatorDTO
        testCoordinator.setPersonalNumber("C-554433");
        testCoordinator.setName("Laura");
        testCoordinator.setPaternalSurname("Gomez");
        testCoordinator.setMaternalSurname("Ruiz");
        testCoordinator.setMonthsOfService(36);
        
        // Assuming a user with ID 1 exists in the database to satisfy the foreign key constraint
        testCoordinator.setUserId(1); 
    }

    /**
     * Tests the successful registration of a new coordinator in the database.
     * Validates that the save() method returns true upon successful SQL INSERT.
     * * @throws PersistenceException If an SQL syntax error or connection drop occurs.
     */
    @Test
    void testSaveCoordinatorSuccess() throws PersistenceException {
        boolean result = coordinatorDAO.save(testCoordinator);
        assertTrue(result, "The coordinator should be saved successfully and return true.");
    }
}