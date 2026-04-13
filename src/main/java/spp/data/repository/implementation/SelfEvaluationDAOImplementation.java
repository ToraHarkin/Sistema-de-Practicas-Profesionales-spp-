package spp.data.repository.implementation;

import spp.data.repository.SelfEvaluationDAO;
import spp.domain.dto.SelfEvaluationDTO;
import spp.data.exception.DataAccessException;
import spp.data.connection.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation for managing intern self-evaluations.
 * Provides full management of evaluation submissions.
 */
public class SelfEvaluationDAOImplementation implements SelfEvaluationDAO {

    /**
     * Submits a new self-evaluation record.
     *
     * @param evaluation DTO containing registration timestamp and intern reference.
     * @return true if successful.
     * @throws DataAccessException If SQL execution fails.
     */
    @Override
    public boolean save(SelfEvaluationDTO evaluation) throws DataAccessException {
        String query = "INSERT INTO autoevaluacion (fecha_registro, id_practicante) VALUES (?, ?)";
        try (Connection conn = ConnectionPool.getInstanceConectionPool().getConnectionPool();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setTimestamp(1, evaluation.getRegistrationDate());
            ps.setInt(2, evaluation.getInternId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataAccessException("Error saving the self evaluation.", e);
        }
    }

    /**
     * Updates an existing self-evaluation metadata.
     *
     * @param evaluation DTO with updated information.
     * @return true if modified.
     * @throws DataAccessException If SQL syntax is incorrect.
     */
    @Override
    public boolean update(SelfEvaluationDTO evaluation) throws DataAccessException {
        String query = "UPDATE autoevaluacion SET fecha_registro = ?, id_practicante = ? WHERE id_autoevaluacion = ?";
        try (Connection conn = ConnectionPool.getInstanceConectionPool().getConnectionPool();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setTimestamp(1, evaluation.getRegistrationDate());
            ps.setInt(2, evaluation.getInternId());
            ps.setInt(3, evaluation.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataAccessException("Error updating the self evaluation.", e);
        }
    }

    /**
     * Retrieves a specific self-evaluation by ID.
     *
     * @param id The internal ID.
     * @return SelfEvaluationDTO or null.
     * @throws DataAccessException If query fails.
     */
    @Override
    public SelfEvaluationDTO getById(int id) throws DataAccessException {
        String query = "SELECT * FROM autoevaluacion WHERE id_autoevaluacion = ?";
        try (Connection conn = ConnectionPool.getInstanceConectionPool().getConnectionPool();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    SelfEvaluationDTO eval = new SelfEvaluationDTO();
                    eval.setId(rs.getInt("id_autoevaluacion"));
                    eval.setRegistrationDate(rs.getTimestamp("fecha_registro"));
                    eval.setInternId(rs.getInt("id_practicante"));
                    return eval;
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error retrieving the self evaluation.", e);
        }
        return null;
    }

    /**
     * Retrieves all self-evaluations submitted by a specific intern.
     *
     * @param internId The internal ID of the intern.
     * @return List of SelfEvaluationDTOs.
     * @throws DataAccessException If data extraction fails.
     */
    @Override
    public List<SelfEvaluationDTO> getByInternId(int internId) throws DataAccessException {
        List<SelfEvaluationDTO> evaluations = new ArrayList<>();
        String query = "SELECT * FROM autoevaluacion WHERE id_practicante = ?";
        try (Connection conn = ConnectionPool.getInstanceConectionPool().getConnectionPool();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, internId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    SelfEvaluationDTO eval = new SelfEvaluationDTO();
                    eval.setId(rs.getInt("id_autoevaluacion"));
                    eval.setRegistrationDate(rs.getTimestamp("fecha_registro"));
                    eval.setInternId(rs.getInt("id_practicante"));
                    evaluations.add(eval);
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error retrieving self evaluations by intern.", e);
        }
        return evaluations;
    }
}