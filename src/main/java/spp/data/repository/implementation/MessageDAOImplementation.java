package spp.data.repository.implementation;

import spp.data.repository.MessageDAO;
import spp.domain.dto.MessageDTO;
import spp.data.exception.DataAccessException;
import spp.data.connection.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation for managing internal messages (inbox system core).
 * Supports creation, modification, deletion, and retrieval of messages.
 */
public class MessageDAOImplementation implements MessageDAO {

    /**
     * Stores a new message in the system.
     *
     * @param message DTO containing subject and body.
     * @return true if successful.
     * @throws DataAccessException If SQL fails.
     */
    @Override
    public boolean save(MessageDTO message) throws DataAccessException {
        String query = "INSERT INTO mensaje (asunto, cuerpo) VALUES (?, ?)";
        try (Connection conn = ConnectionPool.getInstanceConectionPool().getConnectionPool();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, message.getSubject());
            ps.setString(2, message.getBody());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataAccessException("Error saving the message.", e);
        }
    }

    /**
     * Updates an existing message (e.g., modifying a draft before sending).
     *
     * @param message DTO with modified content.
     * @return true if updated.
     * @throws DataAccessException If SQL syntax is incorrect.
     */
    @Override
    public boolean update(MessageDTO message) throws DataAccessException {
        String query = "UPDATE mensaje SET asunto = ?, cuerpo = ? WHERE id_mensaje = ?";
        try (Connection conn = ConnectionPool.getInstanceConectionPool().getConnectionPool();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, message.getSubject());
            ps.setString(2, message.getBody());
            ps.setInt(3, message.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataAccessException("Error updating the message.", e);
        }
    }

    /**
     * Retrieves a single message's details by its ID.
     *
     * @param id The internal identifier.
     * @return MessageDTO or null.
     * @throws DataAccessException If connection drops.
     */
    @Override
    public MessageDTO getById(int id) throws DataAccessException {
        String query = "SELECT * FROM mensaje WHERE id_mensaje = ?";
        try (Connection conn = ConnectionPool.getInstanceConectionPool().getConnectionPool();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    MessageDTO message = new MessageDTO();
                    message.setId(rs.getInt("id_mensaje"));
                    message.setSubject(rs.getString("asunto"));
                    message.setBody(rs.getString("cuerpo"));
                    return message;
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error retrieving the message.", e);
        }
        return null;
    }

    /**
     * Retrieves the complete catalog of raw messages.
     *
     * @return List of MessageDTOs.
     * @throws DataAccessException If data extraction fails.
     */
    @Override
    public List<MessageDTO> getAll() throws DataAccessException {
        List<MessageDTO> messages = new ArrayList<>();
        String query = "SELECT * FROM mensaje";
        try (Connection conn = ConnectionPool.getInstanceConectionPool().getConnectionPool();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                MessageDTO message = new MessageDTO();
                message.setId(rs.getInt("id_mensaje"));
                message.setSubject(rs.getString("asunto"));
                message.setBody(rs.getString("cuerpo"));
                messages.add(message);
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error retrieving messages.", e);
        }
        return messages;
    }
}