package spp.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setScene(scene);
        stage.show();
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
        
        // Nueva Prueba de conexión a la Base de Datos MySQL usando el Pool de Ángel
        try {
            spp.data.connection.ConnectionPool.getInstanceConectionPool().getConnectionPool();
            System.out.println("INFO: ¡Conexión exitosa a la base de datos usando DBCP2!");
        } catch (SQLException e) {
            System.out.println("ERROR FATAL: No se pudo conectar a la base de datos.");
            System.out.println("Detalles: " + e.getMessage());
            // Si la base de datos está caída, el sistema de todos modos abrirá la ventana de JavaFX, 
            // pero ya sabemos por la consola que hay un problema.
        }
        
        launch();
    }
}