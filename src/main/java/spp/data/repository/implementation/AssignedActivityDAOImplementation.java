package spp.data.repository.implementation;

import spp.data.repository.AssignedActivityDAO;
import spp.domain.dto.AssignedActivityDTO;
import spp.data.exception.DataAccessException;
import spp.data.connection.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation for managing activities assigned to interns and their grades.
 */
public class AssignedActivityDAOImplementation implements AssignedActivityDAO {

    @Override
    public boolean save(AssignedActivityDTO assignedActivity) throws DataAccessException {
        String query = "INSERT INTO actividad_asignada_practicante (observaciones, calificacion, ruta_actividad, id_practicante, id_actividad) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionPool.getInstanceConectionPool().getConnectionPool();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, assignedActivity.getObservations());
            ps.setDouble(2, assignedActivity.getGrade());
            ps.setString(3, assignedActivity.getActivityPath());
            ps.setInt(4, assignedActivity.getInternId());
            ps.setInt(5, assignedActivity.getActivityId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataAccessException("Error saving the assigned activity.", e);
        }
    }

    @Override
    public boolean updateGrade(int id, double grade, String observations) throws DataAccessException {
        String query = "UPDATE actividad_asignada_practicante SET calificacion = ?, observaciones = ? WHERE id_actividad_asignada_practicante = ?";
        try (Connection conn = ConnectionPool.getInstanceConectionPool().getConnectionPool();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setDouble(1, grade);
            ps.setString(2, observations);
            ps.setInt(3, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataAccessException("Error updating the assigned activity grade.", e);
        }
    }

    @Override
    public List<AssignedActivityDTO> getByInternId(int internId) throws DataAccessException {
        List<AssignedActivityDTO> assignments = new ArrayList<>();
        String query = "SELECT * FROM actividad_asignada_practicante WHERE id_practicante = ?";
        try (Connection conn = ConnectionPool.getInstanceConectionPool().getConnectionPool();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, internId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    AssignedActivityDTO assignment = new AssignedActivityDTO();
                    assignment.setId(rs.getInt("id_actividad_asignada_practicante"));
                    assignment.setObservations(rs.getString("observaciones"));
                    assignment.setGrade(rs.getDouble("calificacion"));
                    assignment.setActivityPath(rs.getString("ruta_actividad"));
                    assignment.setInternId(rs.getInt("id_practicante"));
                    assignment.setActivityId(rs.getInt("id_actividad"));
                    assignments.add(assignment);
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error retrieving assignments by intern.", e);
        }
        return assignments;
    }
}