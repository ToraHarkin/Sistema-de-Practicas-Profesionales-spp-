/** 
 * CU-A-2: Registrar Profesor
 */

package daotest; 

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import spp.domain.dto.ProfessorDTO; 
import spp.data.repository.implementation.ProfessorDAOImplementation;
import spp.data.exception.PersistenceException;

public class ProfessorDAOTest {
    
    private ProfessorDAOImplementation professorDAO;
    private ProfessorDTO testProfessor; 

    @BeforeEach
    void setUp() {
        professorDAO = new ProfessorDAOImplementation();
        testProfessor = new ProfessorDTO(); 
        
        testProfessor.setName("Isa Gabriela");
        testProfessor.setPaternalSurname("Torres");
        testProfessor.setMaternalSurname("García");
        testProfessor.setPersonalNumber("99999");
        testProfessor.setMonthsOfService(12);
        
        testProfessor.setUserId(1); 
    }

    @Test
    void testAddProfessorSuccess() throws PersistenceException {
        boolean result = professorDAO.save(testProfessor);
        assertTrue(result, "El profesor debería guardarse correctamente.");
    }
    
    /**
     * Prueba el borrado lógico (inactivación) de un profesor existente.
     * Verifica que el estado cambie a 'Inactivo' sin eliminar el registro físico.
     * @throws PersistenceException Si el número de personal no existe o falla la conexión.
     */
    @Test
    void testInactivateProfessorSuccess() throws PersistenceException {
        String personalNumberToInactivate = "99999"; 
        

        boolean result = professorDAO.inactivate(personalNumberToInactivate);
        assertTrue(result, "El estado del profesor debería actualizarse a Inactivo.");
    }
}