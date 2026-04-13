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
        try (Connection conn = ConnectionPool.getInstanceConectionPool().getConnectionPool();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, intern.getEnrollment());
            ps.setString(2, intern.getName());
            ps.setString(3, intern.getPaternalSurname());
            ps.setString(4, intern.getMaternalSurname());
            ps.setInt(5, intern.getAge());
            ps.setString(6, intern.getGender());
            ps.setString(7, intern.getIndigenousLanguage());
            ps.setInt(8, intern.getUserId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
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
        try (Connection conn = ConnectionPool.getInstanceConectionPool().getConnectionPool();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, intern.getName());
            ps.setString(2, intern.getPaternalSurname());
            ps.setString(3, intern.getMaternalSurname());
            ps.setInt(4, intern.getAge());
            ps.setString(5, intern.getGender());
            ps.setString(6, intern.getIndigenousLanguage());
            ps.setString(7, intern.getEnrollment());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
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
        try (Connection conn = ConnectionPool.getInstanceConectionPool().getConnectionPool();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, enrollment);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    InternDTO intern = new InternDTO();
                    intern.setId(rs.getInt("id_practicante"));
                    intern.setEnrollment(rs.getString("matricula"));
                    intern.setName(rs.getString("nombre"));
                    intern.setPaternalSurname(rs.getString("apellido_paterno"));
                    intern.setMaternalSurname(rs.getString("apellido_materno"));
                    intern.setAge(rs.getInt("edad"));
                    intern.setGender(rs.getString("sexo"));
                    intern.setIndigenousLanguage(rs.getString("lengua_indigena"));
                    intern.setUserId(rs.getInt("id_usuario"));
                    return intern;
                }
            }
        } catch (SQLException e) {
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
        try (Connection conn = ConnectionPool.getInstanceConectionPool().getConnectionPool();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                InternDTO intern = new InternDTO();
                intern.setId(rs.getInt("id_practicante"));
                intern.setEnrollment(rs.getString("matricula"));
                intern.setName(rs.getString("nombre"));
                intern.setPaternalSurname(rs.getString("apellido_paterno"));
                intern.setMaternalSurname(rs.getString("apellido_materno"));
                intern.setAge(rs.getInt("edad"));
                intern.setGender(rs.getString("sexo"));
                intern.setIndigenousLanguage(rs.getString("lengua_indigena"));
                intern.setUserId(rs.getInt("id_usuario"));
                interns.add(intern);
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error querying the general intern catalog.", e);
        }
        return interns;
    }
}