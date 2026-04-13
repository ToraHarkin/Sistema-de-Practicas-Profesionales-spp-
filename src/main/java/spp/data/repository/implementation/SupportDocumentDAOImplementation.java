package spp.data.repository.implementation;

import spp.data.repository.SupportDocumentDAO;
import spp.domain.dto.SupportDocumentDTO;
import spp.data.exception.DataAccessException;
import spp.data.connection.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation for managing file metadata and grading.
 */
public class SupportDocumentDAOImplementation implements SupportDocumentDAO {

    /**
     * Saves the metadata of an uploaded document.
     *
     * @param document DTO containing file details.
     * @return true if successful.
     * @throws DataAccessException If SQL syntax is incorrect.
     */
    @Override
    public boolean save(SupportDocumentDTO document) throws DataAccessException {
        String query = "INSERT INTO documento_soporte (tipo, ruta_archivo, extension, tamaño, fecha, id_practicante, id_profesor) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionPool.getInstanceConectionPool().getConnectionPool();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, document.getType());
            ps.setString(2, document.getFilePath());
            ps.setString(3, document.getExtension());
            ps.setLong(4, document.getSize());
            ps.setTimestamp(5, document.getDate());
            ps.setInt(6, document.getInternId());
            ps.setInt(7, document.getProfessorId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataAccessException("Error saving the support document.", e);
        }
    }

    /**
     * Allows a professor to assign a grade and observations to a document.
     *
     * @param documentId The ID of the document.
     * @param grade The assigned grade.
     * @param observations Optional professor comments.
     * @return true if updated.
     * @throws DataAccessException If SQL fails.
     */
    @Override
    public boolean updateGrade(int documentId, double grade, String observations) throws DataAccessException {
        String query = "UPDATE documento_soporte SET calificacion = ?, observaciones = ? WHERE id_documento_soporte = ?";
        try (Connection conn = ConnectionPool.getInstanceConectionPool().getConnectionPool();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setDouble(1, grade);
            ps.setString(2, observations);
            ps.setInt(3, documentId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataAccessException("Error updating the document grade.", e);
        }
    }

    /**
     * Retrieves all documents associated with an intern.
     *
     * @param internId The internal ID of the intern.
     * @return List of SupportDocumentDTOs.
     * @throws DataAccessException If query fails.
     */
    @Override
    public List<SupportDocumentDTO> getByInternId(int internId) throws DataAccessException {
        List<SupportDocumentDTO> documents = new ArrayList<>();
        String query = "SELECT * FROM documento_soporte WHERE id_practicante = ?";
        try (Connection conn = ConnectionPool.getInstanceConectionPool().getConnectionPool();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, internId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    SupportDocumentDTO doc = new SupportDocumentDTO();
                    doc.setId(rs.getInt("id_documento_soporte"));
                    doc.setType(rs.getString("tipo"));
                    doc.setFilePath(rs.getString("ruta_archivo"));
                    doc.setExtension(rs.getString("extension"));
                    doc.setSize(rs.getLong("tamaño"));
                    doc.setDate(rs.getTimestamp("fecha"));
                    doc.setGrade(rs.getDouble("calificacion"));
                    doc.setObservations(rs.getString("observaciones"));
                    doc.setInternId(rs.getInt("id_practicante"));
                    doc.setProfessorId(rs.getInt("id_profesor"));
                    documents.add(doc);
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error retrieving support documents.", e);
        }
        return documents;
    }
}