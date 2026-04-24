package spp.data.repository.implementation;


import spp.data.repository.ProfessorDAO;
import spp.domain.dto.ProfessorDTO;
import spp.data.exception.PersistenceException;
import spp.data.connection.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import spp.data.exception.ConfigurationException;


/**
 * Implementation of the ProfessorDAO interface.
 * Manages the persistence of professors who grade or oversee interns.
 */
public class ProfessorDAOImplementation implements ProfessorDAO {

    /**
     * Saves a new professor in the database.
     *
     * @param professor DTO containing the professor's data.
     * @return true if saved successfully; false otherwise.
     * @throws PersistenceException If a foreign key (user ID) constraint fails.
     */
    @Override
    public boolean save(ProfessorDTO professor) throws PersistenceException {
        String query = "INSERT INTO profesor (numero_personal, nombre, apellido_paterno, apellido_materno, tiempo_servicio_meses, id_usuario) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = ConnectionPool.getInstanceConectionPool().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, professor.getPersonalNumber());
            preparedStatement.setString(2, professor.getName());
            preparedStatement.setString(3, professor.getPaternalSurname());
            preparedStatement.setString(4, professor.getMaternalSurname());
            preparedStatement.setInt(5, professor.getMonthsOfService());
            preparedStatement.setInt(6, professor.getUserId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException | ConfigurationException e) {
            throw new PersistenceException("Error saving the professor.", e);
        }
    }

    /**
     * Updates an existing professor's details based on their personal number.
     *
     * @param professor DTO with the updated data.
     * @return true if updated successfully.
     * @throws PersistenceException If an SQL error occurs.
     */
    @Override
    public boolean update(ProfessorDTO professor) throws PersistenceException {
        String query = "UPDATE profesor SET nombre = ?, apellido_paterno = ?, apellido_materno = ?, tiempo_servicio_meses = ? WHERE numero_personal = ?";
        try (Connection connection = ConnectionPool.getInstanceConectionPool().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, professor.getName());
            preparedStatement.setString(2, professor.getPaternalSurname());
            preparedStatement.setString(3, professor.getMaternalSurname());
            preparedStatement.setInt(4, professor.getMonthsOfService());
            preparedStatement.setString(5, professor.getPersonalNumber());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException | ConfigurationException e) {
            throw new PersistenceException("Error updating the professor.", e);
        }
    }

    /**
     * Retrieves a professor using their unique personal number.
     *
     * @param personalNumber The university identification number.
     * @return ProfessorDTO object or null if not found.
     * @throws PersistenceException If database connection fails.
     */
    @Override
    public ProfessorDTO getByPersonalNumber(String personalNumber) throws PersistenceException {
        String query = "SELECT * FROM profesor WHERE numero_personal = ?";
        try (Connection connection = ConnectionPool.getInstanceConectionPool().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, personalNumber);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    ProfessorDTO prof = new ProfessorDTO();
                    prof.setId(resultSet.getInt("id_profesor"));
                    prof.setPersonalNumber(resultSet.getString("numero_personal"));
                    prof.setName(resultSet.getString("nombre"));
                    prof.setPaternalSurname(resultSet.getString("apellido_paterno"));
                    prof.setMaternalSurname(resultSet.getString("apellido_materno"));
                    prof.setMonthsOfService(resultSet.getInt("tiempo_servicio_meses"));
                    prof.setUserId(resultSet.getInt("id_usuario"));
                    return prof;
                }
            }
        } catch (SQLException | ConfigurationException e) {
            throw new PersistenceException("Error retrieving the professor.", e);
        }
        return null;
    }

    /**
     * Retrieves a list of all professors.
     *
     * @return A list of ProfessorDTO objects.
     * @throws PersistenceException If data extraction fails.
     */
    @Override
    public List<ProfessorDTO> getAll() throws PersistenceException {
        List<ProfessorDTO> professors = new ArrayList<>();
        String query = "SELECT * FROM profesor";
        try (Connection connection = ConnectionPool.getInstanceConectionPool().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                ProfessorDTO prof = new ProfessorDTO();
                prof.setId(resultSet.getInt("id_profesor"));
                prof.setPersonalNumber(resultSet.getString("numero_personal"));
                prof.setName(resultSet.getString("nombre"));
                prof.setPaternalSurname(resultSet.getString("apellido_paterno"));
                prof.setMaternalSurname(resultSet.getString("apellido_materno"));
                prof.setMonthsOfService(resultSet.getInt("tiempo_servicio_meses"));
                prof.setUserId(resultSet.getInt("id_usuario"));
                professors.add(prof);
            }
        } catch (SQLException | ConfigurationException e) {
            throw new PersistenceException("Error retrieving the list of professors.", e);
        }
        return professors;
    }
    
    /**
     * Inactivates a professor by updating the linked user's status to 'Inactivo'.
     *
     * @param personalNumber The university identification number.
     * @return true if updated successfully.
     * @throws PersistenceException If an SQL error occurs.
     */
    @Override
    public boolean inactivate(String personalNumber) throws PersistenceException {
        // Actualizamos el estado en la tabla usuario usando el id vinculado al profesor
        String query = "UPDATE usuario SET estado = 'Inactivo' WHERE id_usuario = (SELECT id_usuario FROM profesor WHERE numero_personal = ?)";
        try (Connection connection = ConnectionPool.getInstanceConectionPool().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, personalNumber);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException | ConfigurationException e) {
            throw new PersistenceException("Error inactivating the professor.", e);
        }
    }
}