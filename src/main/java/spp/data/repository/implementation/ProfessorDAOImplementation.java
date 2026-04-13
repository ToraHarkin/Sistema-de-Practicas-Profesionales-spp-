package spp.data.repository.implementation;

import spp.data.repository.ProfessorDAO;
import spp.domain.dto.ProfessorDTO;
import spp.data.exception.DataAccessException;
import spp.data.connection.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
     * @throws DataAccessException If a foreign key (user ID) constraint fails.
     */
    @Override
    public boolean save(ProfessorDTO professor) throws DataAccessException {
        String query = "INSERT INTO profesor (numero_personal, nombre, apellido_paterno, apellido_materno, tiempo_servicio_meses, id_usuario) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionPool.getInstanceConectionPool().getConnectionPool();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, professor.getPersonalNumber());
            ps.setString(2, professor.getName());
            ps.setString(3, professor.getPaternalSurname());
            ps.setString(4, professor.getMaternalSurname());
            ps.setInt(5, professor.getMonthsOfService());
            ps.setInt(6, professor.getUserId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataAccessException("Error saving the professor.", e);
        }
    }

    /**
     * Updates an existing professor's details based on their personal number.
     *
     * @param professor DTO with the updated data.
     * @return true if updated successfully.
     * @throws DataAccessException If an SQL error occurs.
     */
    @Override
    public boolean update(ProfessorDTO professor) throws DataAccessException {
        String query = "UPDATE profesor SET nombre = ?, apellido_paterno = ?, apellido_materno = ?, tiempo_servicio_meses = ? WHERE numero_personal = ?";
        try (Connection conn = ConnectionPool.getInstanceConectionPool().getConnectionPool();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, professor.getName());
            ps.setString(2, professor.getPaternalSurname());
            ps.setString(3, professor.getMaternalSurname());
            ps.setInt(4, professor.getMonthsOfService());
            ps.setString(5, professor.getPersonalNumber());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataAccessException("Error updating the professor.", e);
        }
    }

    /**
     * Retrieves a professor using their unique personal number.
     *
     * @param personalNumber The university identification number.
     * @return ProfessorDTO object or null if not found.
     * @throws DataAccessException If database connection fails.
     */
    @Override
    public ProfessorDTO getByPersonalNumber(String personalNumber) throws DataAccessException {
        String query = "SELECT * FROM profesor WHERE numero_personal = ?";
        try (Connection conn = ConnectionPool.getInstanceConectionPool().getConnectionPool();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, personalNumber);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ProfessorDTO prof = new ProfessorDTO();
                    prof.setId(rs.getInt("id_profesor"));
                    prof.setPersonalNumber(rs.getString("numero_personal"));
                    prof.setName(rs.getString("nombre"));
                    prof.setPaternalSurname(rs.getString("apellido_paterno"));
                    prof.setMaternalSurname(rs.getString("apellido_materno"));
                    prof.setMonthsOfService(rs.getInt("tiempo_servicio_meses"));
                    prof.setUserId(rs.getInt("id_usuario"));
                    return prof;
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error retrieving the professor.", e);
        }
        return null;
    }

    /**
     * Retrieves a list of all professors.
     *
     * @return A list of ProfessorDTO objects.
     * @throws DataAccessException If data extraction fails.
     */
    @Override
    public List<ProfessorDTO> getAll() throws DataAccessException {
        List<ProfessorDTO> professors = new ArrayList<>();
        String query = "SELECT * FROM profesor";
        try (Connection conn = ConnectionPool.getInstanceConectionPool().getConnectionPool();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ProfessorDTO prof = new ProfessorDTO();
                prof.setId(rs.getInt("id_profesor"));
                prof.setPersonalNumber(rs.getString("numero_personal"));
                prof.setName(rs.getString("nombre"));
                prof.setPaternalSurname(rs.getString("apellido_paterno"));
                prof.setMaternalSurname(rs.getString("apellido_materno"));
                prof.setMonthsOfService(rs.getInt("tiempo_servicio_meses"));
                prof.setUserId(rs.getInt("id_usuario"));
                professors.add(prof);
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error retrieving the list of professors.", e);
        }
        return professors;
    }
}