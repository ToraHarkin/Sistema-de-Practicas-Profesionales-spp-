package spp.data.repository.implementation;


import spp.data.repository.InternDAO;
import spp.domain.dto.InternDTO;
import spp.data.exception.DataAccessException;
import spp.data.connection.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import spp.data.exception.ConfigurationException;


/**
 * Implementation of the InternDAO interface for intern persistence.
 * Manages data access operations using a DBCP2 connection pool.
 */
public class InternDAOImplementation implements InternDAO {

    /**
     * Registers a new intern in the database.
     *
     * @param intern Data Transfer Object containing the intern's(practicante) information.
     * @return true if the insertion was successful; false otherwise.
     * @throws DataAccessException If a technical error occurs while communicating with the database.
     */
    @Override
    public boolean save(InternDTO intern) throws DataAccessException {
        String query = "INSERT INTO practicante (matricula, nombre, apellido_paterno, apellido_materno, edad, sexo, lengua_indigena, id_usuario) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = ConnectionPool.getInstanceConectionPool().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, intern.getEnrollment());
            preparedStatement.setString(2, intern.getName());
            preparedStatement.setString(3, intern.getPaternalSurname());
            preparedStatement.setString(4, intern.getMaternalSurname());
            preparedStatement.setInt(5, intern.getAge());
            preparedStatement.setString(6, intern.getGender());
            preparedStatement.setString(7, intern.getIndigenousLanguage());
            preparedStatement.setInt(8, intern.getUserId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException | ConfigurationException e) {
            throw new DataAccessException("Error saving the intern to the database.", e);
        }
    }

    /**
     * Updates the personal information of an existing intern.
     *
     * @param intern Object containing the updated data. The enrollment acts as the identifier.
     * @return true if the update modified the record; false if it was not found.
     * @throws DataAccessException If an error occurs during the execution of the SQL statement.
     */
    @Override
    public boolean update(InternDTO intern) throws DataAccessException {
        String query = "UPDATE practicante SET nombre = ?, apellido_paterno = ?, apellido_materno = ?, edad = ?, sexo = ?, lengua_indigena = ? WHERE matricula = ?";
        try (Connection connection = ConnectionPool.getInstanceConectionPool().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, intern.getName());
            preparedStatement.setString(2, intern.getPaternalSurname());
            preparedStatement.setString(3, intern.getMaternalSurname());
            preparedStatement.setInt(4, intern.getAge());
            preparedStatement.setString(5, intern.getGender());
            preparedStatement.setString(6, intern.getIndigenousLanguage());
            preparedStatement.setString(7, intern.getEnrollment());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException | ConfigurationException e) {
            throw new DataAccessException("Error updating the intern's information.", e);
        }
    }

    /**
     * Retrieves the complete profile of an intern by their enrollment.
     *
     * @param enrollment The intern's unique enrollment identifier.
     * @return An InternDTO object with the data, or null if no matching record exists.
     * @throws DataAccessException If the connection is interrupted or the query fails.
     */
    @Override
    public InternDTO getByEnrollment(String enrollment) throws DataAccessException {
        String query = "SELECT * FROM practicante WHERE matricula = ?";
        try (Connection connection = ConnectionPool.getInstanceConectionPool().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, enrollment);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    InternDTO intern = new InternDTO();
                    intern.setId(resultSet.getInt("id_practicante"));
                    intern.setEnrollment(resultSet.getString("matricula"));
                    intern.setName(resultSet.getString("nombre"));
                    intern.setPaternalSurname(resultSet.getString("apellido_paterno"));
                    intern.setMaternalSurname(resultSet.getString("apellido_materno"));
                    intern.setAge(resultSet.getInt("edad"));
                    intern.setGender(resultSet.getString("sexo"));
                    intern.setIndigenousLanguage(resultSet.getString("lengua_indigena"));
                    intern.setUserId(resultSet.getInt("id_usuario"));
                    return intern;
                }
            }
        } catch (SQLException | ConfigurationException e) {
            throw new DataAccessException("Error querying the intern by enrollment.", e);
        }
        return null;
    }

    /**
     * Retrieves a list of all interns registered in the system.
     *
     * @return A list of InternDTO objects. If there are no records, it returns an empty list.
     * @throws DataAccessException If a problem occurs while extracting data from the database.
     */
    @Override
    public List<InternDTO> getAll() throws DataAccessException {
        List<InternDTO> interns = new ArrayList<>();
        String query = "SELECT * FROM practicante";
        try (Connection connection = ConnectionPool.getInstanceConectionPool().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                InternDTO intern = new InternDTO();
                intern.setId(resultSet.getInt("id_practicante"));
                intern.setEnrollment(resultSet.getString("matricula"));
                intern.setName(resultSet.getString("nombre"));
                intern.setPaternalSurname(resultSet.getString("apellido_paterno"));
                intern.setMaternalSurname(resultSet.getString("apellido_materno"));
                intern.setAge(resultSet.getInt("edad"));
                intern.setGender(resultSet.getString("sexo"));
                intern.setIndigenousLanguage(resultSet.getString("lengua_indigena"));
                intern.setUserId(resultSet.getInt("id_usuario"));
                interns.add(intern);
            }
        } catch (SQLException | ConfigurationException e) {
            throw new DataAccessException("Error querying the general intern catalog.", e);
        }
        return interns;
    }
}