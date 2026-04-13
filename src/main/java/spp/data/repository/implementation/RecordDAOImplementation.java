package spp.data.repository.implementation;

import spp.data.repository.RecordDAO;
import spp.domain.dto.RecordDTO;
import spp.data.exception.DataAccessException;
import spp.data.connection.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Implementation for managing the intern's academic record and final grading.
 */
public class RecordDAOImplementation implements RecordDAO {

    /**
     * Initializes a new academic record for an intern.
     *
     * @param record DTO containing references to intern, professor, and project.
     * @return true if successful.
     * @throws DataAccessException If foreign keys are violated.
     */
    @Override
    public boolean save(RecordDTO record) throws DataAccessException {
        String query = "INSERT INTO expediente (promedio_final, id_practicante, id_proyecto, id_profesor, id_periodo_escolar) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionPool.getInstanceConectionPool().getConnectionPool();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setDouble(1, record.getFinalGrade());
            ps.setInt(2, record.getInternId());
            
            if (record.getProjectId() != null) { ps.setInt(3, record.getProjectId()); } 
            else { ps.setNull(3, java.sql.Types.INTEGER); }
            
            if (record.getProfessorId() != null) { ps.setInt(4, record.getProfessorId()); } 
            else { ps.setNull(4, java.sql.Types.INTEGER); }
            
            ps.setInt(5, record.getSchoolPeriodId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataAccessException("Error creating the academic record.", e);
        }
    }

    /**
     * Updates the final grade of a completed record.
     *
     * @param recordId Internal ID of the record.
     * @param finalGrade The calculated final grade.
     * @return true if updated.
     * @throws DataAccessException If SQL fails.
     */
    @Override
    public boolean updateFinalGrade(int recordId, double finalGrade) throws DataAccessException {
        String query = "UPDATE expediente SET promedio_final = ? WHERE id_expediente = ?";
        try (Connection conn = ConnectionPool.getInstanceConectionPool().getConnectionPool();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setDouble(1, finalGrade);
            ps.setInt(2, recordId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataAccessException("Error updating the final grade.", e);
        }
    }

    /**
     * Retrieves the complete dossier for a specific intern.
     *
     * @param internId The intern's identifier.
     * @return RecordDTO object or null.
     * @throws DataAccessException If query fails.
     */
    @Override
    public RecordDTO getByInternId(int internId) throws DataAccessException {
        String query = "SELECT * FROM expediente WHERE id_practicante = ?";
        try (Connection conn = ConnectionPool.getInstanceConectionPool().getConnectionPool();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, internId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    RecordDTO record = new RecordDTO();
                    record.setId(rs.getInt("id_expediente"));
                    record.setFinalGrade(rs.getDouble("promedio_final"));
                    record.setInternId(rs.getInt("id_practicante"));
                    
                    int projectId = rs.getInt("id_proyecto");
                    record.setProjectId(rs.wasNull() ? null : projectId);
                    
                    int professorId = rs.getInt("id_profesor");
                    record.setProfessorId(rs.wasNull() ? null : professorId);
                    
                    record.setSchoolPeriodId(rs.getInt("id_periodo_escolar"));
                    return record;
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error retrieving the academic record.", e);
        }
        return null;
    }
}