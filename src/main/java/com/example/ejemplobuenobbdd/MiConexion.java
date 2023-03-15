package com.example.ejemplobuenobbdd;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MiConexion {

    // Atributos
    private static Connection conexion = null;
    private static Properties props = null;

    // Metodos
    private MiConexion() throws SQLException, IOException {
    props = GestionPropertiesConexion.getProperties("Conexion.properties");
    String url = props.getProperty("url");
    String usr = props.getProperty("usr");
    String pwd = props.getProperty("pwd");
    conexion = DriverManager.getConnection(url,usr,pwd);
    }
    public static Connection abrirConexion() throws SQLException, IOException {
        if (conexion == null || conexion.isClosed()) new MiConexion();
        return conexion;
    }
}
