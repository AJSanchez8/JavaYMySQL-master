package com.example.ejemplobuenobbdd;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AppIndex extends Application {

    public static Connection conexion;
    @Override
    public void start(Stage stage) throws IOException {

        // Scene
        FXMLLoader fxmlLoader = new FXMLLoader(AppIndex.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        // Stage
        stage.setTitle("Aplicacion Bases de datos");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }

    public static void main(String[] args) throws SQLException, IOException {
        conexion = MiConexion.abrirConexion();
        if (conexion!=null && conexion.isClosed()){
            System.out.println("Conextando a bases de datos");

        }
        System.out.println("Conexion: "+conexion);

        launch();
    }
    // BLoque estatico para quitar conexión
    static {Runtime.getRuntime().addShutdownHook(new CierraConexion());}
}