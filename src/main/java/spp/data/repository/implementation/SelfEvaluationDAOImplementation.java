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
import spp.data.exception.ConfigurationException;


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
        try (Connection connection = ConnectionPool.getInstanceConectionPool().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setTimestamp(1, evaluation.getRegistrationDate());
            preparedStatement.setInt(2, evaluation.getInternId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException | ConfigurationException e) {
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
        try (Connection connection = ConnectionPool.getInstanceConectionPool().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setTimestamp(1, evaluation.getRegistrationDate());
            preparedStatement.setInt(2, evaluation.getInternId());
            preparedStatement.setInt(3, evaluation.getId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException | ConfigurationException e) {
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
        try (Connection connection = ConnectionPool.getInstanceConectionPool().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    SelfEvaluationDTO eval = new SelfEvaluationDTO();
                    eval.setId(resultSet.getInt("id_autoevaluacion"));
                    eval.setRegistrationDate(resultSet.getTimestamp("fecha_registro"));
                    eval.setInternId(resultSet.getInt("id_practicante"));
                    return eval;
                }
            }
        } catch (SQLException | ConfigurationException e) {
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
        try (Connection connection = ConnectionPool.getInstanceConectionPool().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, internId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    SelfEvaluationDTO eval = new SelfEvaluationDTO();
                    eval.setId(resultSet.getInt("id_autoevaluacion"));
                    eval.setRegistrationDate(resultSet.getTimestamp("fecha_registro"));
                    eval.setInternId(resultSet.getInt("id_practicante"));
                    evaluations.add(eval);
                }
            }
        } catch (SQLException | ConfigurationException e) {
            throw new DataAccessException("Error retrieving self evaluations by intern.", e);
        }
        return evaluations;
    }
}