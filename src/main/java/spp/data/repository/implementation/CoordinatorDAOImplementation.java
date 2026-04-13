package spp.data.repository.implementation;

import spp.data.repository.CoordinatorDAO;
import spp.domain.dto.CoordinatorDTO;
import spp.data.exception.DataAccessException;
import spp.data.connection.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation for managing system coordinators.
 */
public class CoordinatorDAOImplementation implements CoordinatorDAO {

    /**
     * Registers a new coordinator in the system.
     *
     * @param coordinator DTO containing the coordinator's details.
     * @return true if successful.
     * @throws DataAccessException If database communication fails.
     */
    @Override
    public boolean save(CoordinatorDTO coordinator) throws DataAccessException {
        String query = "INSERT INTO coordinador (numero_personal, nombre, apellido_paterno, apellido_materno, tiempo_servicio_meses, id_usuario) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionPool.getInstanceConectionPool().getConnectionPool();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, coordinator.getPersonalNumber());
            ps.setString(2, coordinator.getName());
            ps.setString(3, coordinator.getPaternalSurname());
            ps.setString(4, coordinator.getMaternalSurname());
            ps.setInt(5, coordinator.getMonthsOfService());
            ps.setInt(6, coordinator.getUserId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataAccessException("Error saving the coordinator.", e);
        }
    }

    /**
     * Updates an existing coordinator's information.
     *
     * @param coordinator DTO with updated data.
     * @return true if updated.
     * @throws DataAccessException If SQL execution fails.
     */
    @Override
    public boolean update(CoordinatorDTO coordinator) throws DataAccessException {
        String query = "UPDATE coordinador SET nombre = ?, apellido_paterno = ?, apellido_materno = ?, tiempo_servicio_meses = ? WHERE numero_personal = ?";
        try (Connection conn = ConnectionPool.getInstanceConectionPool().getConnectionPool();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, coordinator.getName());
            ps.setString(2, coordinator.getPaternalSurname());
            ps.setString(3, coordinator.getMaternalSurname());
            ps.setInt(4, coordinator.getMonthsOfService());
            ps.setString(5, coordinator.getPersonalNumber());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataAccessException("Error updating the coordinator.", e);
        }
    }

    /**
     * Retrieves a coordinator using their unique personal number.
     *
     * @param personalNumber The university identification number.
     * @return CoordinatorDTO object or null if not found.
     * @throws DataAccessException If query fails.
     */
    @Override
    public CoordinatorDTO getByPersonalNumber(String personalNumber) throws DataAccessException {
        String query = "SELECT * FROM coordinador WHERE numero_personal = ?";
        try (Connection conn = ConnectionPool.getInstanceConectionPool().getConnectionPool();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, personalNumber);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    CoordinatorDTO coordinator = new CoordinatorDTO();
                    coordinator.setId(rs.getInt("id_coordinador"));
                    coordinator.setPersonalNumber(rs.getString("numero_personal"));
                    coordinator.setName(rs.getString("nombre"));
                    coordinator.setPaternalSurname(rs.getString("apellido_paterno"));
                    coordinator.setMaternalSurname(rs.getString("apellido_materno"));
                    coordinator.setMonthsOfService(rs.getInt("tiempo_servicio_meses"));
                    coordinator.setUserId(rs.getInt("id_usuario"));
                    return coordinator;
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error retrieving the coordinator.", e);
        }
        return null;
    }

    /**
     * Retrieves a list of all coordinators.
     *
     * @return List of CoordinatorDTOs.
     * @throws DataAccessException If data extraction fails.
     */
    @Override
    public List<CoordinatorDTO> getAll() throws DataAccessException {
        List<CoordinatorDTO> coordinators = new ArrayList<>();
        String query = "SELECT * FROM coordinador";
        try (Connection conn = ConnectionPool.getInstanceConectionPool().getConnectionPool();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                CoordinatorDTO coordinator = new CoordinatorDTO();
                coordinator.setId(rs.getInt("id_coordinador"));
                coordinator.setPersonalNumber(rs.getString("numero_personal"));
                coordinator.setName(rs.getString("nombre"));
                coordinator.setPaternalSurname(rs.getString("apellido_paterno"));
                coordinator.setMaternalSurname(rs.getString("apellido_materno"));
                coordinator.setMonthsOfService(rs.getInt("tiempo_servicio_meses"));
                coordinator.setUserId(rs.getInt("id_usuario"));
                coordinators.add(coordinator);
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error retrieving coordinators.", e);
        }
        return coordinators;
    }
}