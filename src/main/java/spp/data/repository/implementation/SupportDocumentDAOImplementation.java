package spp.data.repository.implementation;


import spp.data.repository.SupportDocumentDAO;
import spp.domain.dto.SupportDocumentDTO;
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
 * Implementation for managing file metadata and grading.
 */
public class SupportDocumentDAOImplementation implements SupportDocumentDAO {

    /**
     * Saves the metadata of an uploaded document.
     *
     * @param document DTO containing file details.
     * @return true if successful.
     * @throws PersistenceException If SQL syntax is incorrect.
     */
    @Override
    public boolean save(SupportDocumentDTO document) throws PersistenceException {
        String query = "INSERT INTO documento_soporte (tipo, ruta_archivo, extension, tamaño, fecha, id_practicante, id_profesor) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = ConnectionPool.getInstanceConectionPool().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, document.getType());
            preparedStatement.setString(2, document.getFilePath());
            preparedStatement.setString(3, document.getExtension());
            preparedStatement.setLong(4, document.getSize());
            preparedStatement.setTimestamp(5, document.getDate());
            preparedStatement.setInt(6, document.getInternId());
            preparedStatement.setInt(7, document.getProfessorId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException | ConfigurationException e) {
            throw new PersistenceException("Error saving the support document.", e);
        }
    }

    /**
     * Allows a professor to assign a grade and observations to a document.
     *
     * @param documentId The ID of the document.
     * @param grade The assigned grade.
     * @param observations Optional professor comments.
     * @return true if updated.
     * @throws PersistenceException If SQL fails.
     */
    @Override
    public boolean updateGrade(int documentId, double grade, String observations) throws PersistenceException {
        String query = "UPDATE documento_soporte SET calificacion = ?, observaciones = ? WHERE id_documento_soporte = ?";
        try (Connection connection = ConnectionPool.getInstanceConectionPool().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDouble(1, grade);
            preparedStatement.setString(2, observations);
            preparedStatement.setInt(3, documentId);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException | ConfigurationException e) {
            throw new PersistenceException("Error updating the document grade.", e);
        }
    }

    /**
     * Retrieves all documents associated with an intern.
     *
     * @param internId The internal ID of the intern.
     * @return List of SupportDocumentDTOs.
     * @throws PersistenceException If query fails.
     */
    @Override
    public List<SupportDocumentDTO> getByInternId(int internId) throws PersistenceException {
        List<SupportDocumentDTO> documents = new ArrayList<>();
        String query = "SELECT * FROM documento_soporte WHERE id_practicante = ?";
        try (Connection connection = ConnectionPool.getInstanceConectionPool().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, internId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    SupportDocumentDTO doc = new SupportDocumentDTO();
                    doc.setId(resultSet.getInt("id_documento_soporte"));
                    doc.setType(resultSet.getString("tipo"));
                    doc.setFilePath(resultSet.getString("ruta_archivo"));
                    doc.setExtension(resultSet.getString("extension"));
                    doc.setSize(resultSet.getLong("tamaño"));
                    doc.setDate(resultSet.getTimestamp("fecha"));
                    doc.setGrade(resultSet.getDouble("calificacion"));
                    doc.setObservations(resultSet.getString("observaciones"));
                    doc.setInternId(resultSet.getInt("id_practicante"));
                    doc.setProfessorId(resultSet.getInt("id_profesor"));
                    documents.add(doc);
                }
            }
        } catch (SQLException  | ConfigurationException e) {
            throw new PersistenceException("Error retrieving support documents.", e);
        }
        return documents;
    }
}