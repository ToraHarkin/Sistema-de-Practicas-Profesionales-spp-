package spp.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;
import spp.data.exception.ConfigurationException;
import spp.data.exception.PersistenceException;
import spp.data.repository.implementation.AdministratorDAOImplementation;


public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        String initialView = determinateInitialView();
        scene = new Scene(loadFXML(initialView));
        stage.setScene(scene);
        stage.show();
    }
    
    private String determinateInitialView(){ 
        try {
            AdministratorDAOImplementation adminDAO = 
                    new AdministratorDAOImplementation();
            
            boolean existAdmin = adminDAO.existsAdministrator();
            
            if(existAdmin) {
                return "welcome";
            } else {
                return "welcome";
            }
        } catch(PersistenceException e) {
            System.out.println("ERROR: No se pudo validar existencia de administradores.");
            System.out.println("Detalles: " + e.getMessage());
            return "welcome";
        }
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        System.out.println("INFO: Arrancando el Sistema de Practicas Profesionales...");
        
        try {
            spp.data.connection.ConnectionPool.getInstanceConectionPool().getConnection();
            System.out.println("INFO: ¡Conexión exitosa a la base de datos usando DBCP2!");
        } catch (SQLException  | ConfigurationException e) {
            System.out.println("ERROR FATAL: No se pudo conectar a la base de datos.");
            System.out.println("Detalles: " + e.getMessage());
        }
        
        launch();
    }
}