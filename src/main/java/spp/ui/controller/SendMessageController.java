package spp.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import spp.domain.dto.MessageDTO;
import spp.data.repository.implementation.MessageDAOImplementation;
import spp.data.exception.PersistenceException;

/**
 * Controller for the Send Message user interface.
 * Manages the creation and persistence of internal messages.
 */
public class SendMessageController {

    @FXML private TextField txtSubject;
    @FXML private TextArea txtBody;

    private final MessageDAOImplementation messageDAO = new MessageDAOImplementation();

    /**
     * Handles the send button action. Validates input and persists
     * the message DTO through the DAO implementation.
     */
    @FXML
    public void handleSendMessage() {
        try {
            if (txtSubject.getText().isEmpty() || txtBody.getText().isEmpty()) {
                showAlert(AlertType.WARNING, "Warning", "Subject and Body cannot be empty.");
                return;
            }

            MessageDTO message = new MessageDTO();
            message.setSubject(txtSubject.getText());
            message.setBody(txtBody.getText());

            boolean success = messageDAO.save(message);

            if (success) {
                showAlert(AlertType.INFORMATION, "Success", "Message sent successfully.");
                clearFields();
            }

        } catch (PersistenceException e) {
            System.err.println("Technical Error (DataBase): " + e.getMessage());
            showAlert(AlertType.ERROR, 
                      "Delivery Failed", 
                      "We couldn't send your message at this time. The service might be temporarily unavailable. Please try again later.");
        }
    }

    private void showAlert(AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearFields() {
        txtSubject.clear();
        txtBody.clear();
    }
}