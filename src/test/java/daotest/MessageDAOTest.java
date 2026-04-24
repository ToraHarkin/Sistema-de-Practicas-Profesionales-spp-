/**
 * CU-U-2 (Enviar Mensaje)
 */

package daotest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import spp.domain.dto.MessageDTO;
import spp.data.repository.implementation.MessageDAOImplementation;
import spp.data.exception.PersistenceException;

/**
 * Pruebas unitarias para MessageDAOImplementation.
 * Verifica la correcta inserción de la información base de los mensajes (asunto y cuerpo)
 * en la base de datos del sistema.
 */
public class MessageDAOTest {

    private MessageDAOImplementation messageDAO;
    private MessageDTO testMessage;

    /**
     * Configura el entorno de prueba instanciando el motor de persistencia (DAO) 
     * y preparando un contenedor de datos (DTO) simulado.
     */
    @BeforeEach
    void setUp() {
        messageDAO = new MessageDAOImplementation();
        testMessage = new MessageDTO();
        
        // Usando los métodos exactos de tu MessageDTO
        testMessage.setSubject("Aviso de Reunión Urgente");
        testMessage.setBody("Se les convoca a la reunión de revisión de avances del proyecto a las 10:00 AM. Favor de ser puntuales.");
    }

    /**
     * Prueba el guardado exitoso de un nuevo mensaje en la base de datos.
     * Valida que el método save() retorne true al ejecutar el INSERT correctamente.
     * * @throws PersistenceException Si ocurre un error de sintaxis SQL o problemas de conexión.
     */
    @Test
    void testSaveMessageSuccess() throws PersistenceException {
        boolean result = messageDAO.save(testMessage);
        assertTrue(result, "El mensaje debería guardarse en el sistema correctamente y retornar true.");
    }
}