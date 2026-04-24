package spp.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import spp.domain.dto.ProfessorDTO;
import spp.data.repository.implementation.ProfessorDAOImplementation;
import spp.data.exception.PersistenceException;

/**
 * Controller class for the Register Professor user interface.
 * Handles user input, interaction with the data access object (DAO),
 * and manages UI feedback including exception handling.
 */
public class RegisterProfessorController {

    @FXML private TextField txtName;
    @FXML private TextField txtPaternalSurname;
    @FXML private TextField txtMaternalSurname;
    @FXML private TextField txtPersonalNumber;
    @FXML private TextField txtMonthsOfService;

    private final ProfessorDAOImplementation professorDAO = new ProfessorDAOImplementation();

    /**
     * Triggered when the user clicks the 'Registrar' button.
     * Validates input, constructs a ProfessorDTO, and attempts to save it
     * to the database. Catches and handles persistence or formatting errors.
     */
    @FXML
    public void handleRegistrarProfesor() {
        try {
            ProfessorDTO newProfessor = new ProfessorDTO();
            newProfessor.setName(txtName.getText());
            newProfessor.setPaternalSurname(txtPaternalSurname.getText());
            newProfessor.setMaternalSurname(txtMaternalSurname.getText());
            newProfessor.setPersonalNumber(txtPersonalNumber.getText());
            newProfessor.setMonthsOfService(Integer.parseInt(txtMonthsOfService.getText()));
            
            newProfessor.setUserId(1); 

            boolean success = professorDAO.save(newProfessor);

            if (success) {
                showAlert(AlertType.INFORMATION, "Success", "The professor was registered successfully.");
                clearFields();
            }

        } catch (PersistenceException e) {
            System.err.println("Technical Error (DataBase): " + e.getMessage());
            showAlert(AlertType.ERROR, 
                      "Registration Error", 
                      "Could not register the professor. The Personal Number might already exist or the system is currently unavailable. Please check the data and try again.");
        
        } catch (NumberFormatException e) {
    }}

    /**
     * Displays a JavaFX Alert dialog to the user.
     *
     * @param type    The type of alert (INFORMATION, ERROR, WARNING, etc.)
     * @param title   The title of the alert window
     * @param message The content message to display
     */
    private void showAlert(AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Clears all text fields in the form after a successful registration.
     */
    private void clearFields() {
        txtName.clear();
        txtPaternalSurname.clear();
        txtMaternalSurname.clear();
        txtPersonalNumber.clear();
        txtMonthsOfService.clear();
    }
}
