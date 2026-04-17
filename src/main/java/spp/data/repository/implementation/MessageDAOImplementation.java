package spp.data.repository.implementation;


import spp.data.repository.MessageDAO;
import spp.domain.dto.MessageDTO;
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
 * Implementation for managing internal messages (inbox system core).
 * Supports creation, modification, deletion, and retrieval of messages.
 */
public class MessageDAOImplementation implements MessageDAO {

    /**
     * Stores a new message in the system.
     *
     * @param message DTO containing subject and body.
     * @return true if successful.
     * @throws PersistenceException If SQL fails.
     */
    @Override
    public boolean save(MessageDTO message) throws PersistenceException {
        String query = "INSERT INTO mensaje (asunto, cuerpo) VALUES (?, ?)";
        try (Connection connection = ConnectionPool.getInstanceConectionPool().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, message.getSubject());
            preparedStatement.setString(2, message.getBody());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException | ConfigurationException e) {
            throw new PersistenceException("Error saving the message.", e);
        }
    }

    /**
     * Updates an existing message (e.g., modifying a draft before sending).
     *
     * @param message DTO with modified content.
     * @return true if updated.
     * @throws PersistenceException If SQL syntax is incorrect.
     */
    @Override
    public boolean update(MessageDTO message) throws PersistenceException {
        String query = "UPDATE mensaje SET asunto = ?, cuerpo = ? WHERE id_mensaje = ?";
        try (Connection connection = ConnectionPool.getInstanceConectionPool().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, message.getSubject());
            preparedStatement.setString(2, message.getBody());
            preparedStatement.setInt(3, message.getId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException | ConfigurationException e) {
            throw new PersistenceException("Error updating the message.", e);
        }
    }

    /**
     * Retrieves a single message's details by its ID.
     *
     * @param id The internal identifier.
     * @return MessageDTO or null.
     * @throws PersistenceException If connection drops.
     */
    @Override
    public MessageDTO getById(int id) throws PersistenceException {
        String query = "SELECT * FROM mensaje WHERE id_mensaje = ?";
        try (Connection connection = ConnectionPool.getInstanceConectionPool().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    MessageDTO message = new MessageDTO();
                    message.setId(resultSet.getInt("id_mensaje"));
                    message.setSubject(resultSet.getString("asunto"));
                    message.setBody(resultSet.getString("cuerpo"));
                    return message;
                }
            }
        } catch (SQLException | ConfigurationException e) {
            throw new PersistenceException("Error retrieving the message.", e);
        }
        return null;
    }

    /**
     * Retrieves the complete catalog of raw messages.
     *
     * @return List of MessageDTOs.
     * @throws PersistenceException If data extraction fails.
     */
    @Override
    public List<MessageDTO> getAll() throws PersistenceException {
        List<MessageDTO> messages = new ArrayList<>();
        String query = "SELECT * FROM mensaje";
        try (Connection connection = ConnectionPool.getInstanceConectionPool().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                MessageDTO message = new MessageDTO();
                message.setId(resultSet.getInt("id_mensaje"));
                message.setSubject(resultSet.getString("asunto"));
                message.setBody(resultSet.getString("cuerpo"));
                messages.add(message);
            }
        } catch (SQLException | ConfigurationException e) {
            throw new PersistenceException("Error retrieving messages.", e);
        }
        return messages;
    }
}