package com.example.ejemplobuenobbdd;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AppController {
    @FXML
    private TextField entrada;
    @FXML
    private Text salida;
    @FXML
    private TextArea contenido;
    @FXML
    private TextField textoBorrar;
    @FXML
    private Button botonBorrar;
    @FXML
    protected void onInsertButtonClick() throws SQLException {

        String valor = entrada.getText();
        if (!valor.equals("")){
            String sql = """
                    INSERT INTO prueba (texto) VALUES (?)
                    """;
            PreparedStatement pstmt = AppIndex.conexion.prepareStatement(sql);
            pstmt.setString(1,valor);
            int n = pstmt.executeUpdate();
            if (n>0){
                System.out.println("Registro insertado con exito");
                salida.setText("Registo insertado con exito");
                Statement stm = AppIndex.conexion.createStatement();
                String sql2 = """
                        SELECT * FROM prueba
                        """;
                ResultSet rs = stm.executeQuery(sql2);
                String temp="";
                while (rs.next()){
                    temp += rs.getInt(1)+" "+rs.getString(2)+"\n" ;
                }
                contenido.setText(temp);
            }
        }else {
            salida.setText(" ERROR CAMPO VACÍO ");
            System.out.println(" ERROR CAMPO VACÍO ");
        }

        String sqlCheckEspacio = """
                SELECT COUNT(*) FROM pruebas
                """;
        PreparedStatement pstmtChech = AppIndex.conexion.prepareStatement(sqlCheckEspacio);
        if (!pstmtChech.equals("")){
            botonBorrar.setVisible(true);
            textoBorrar.setVisible(true);
        }

    }
    @FXML
    protected void onDeleteButtonClick() throws SQLException {
        String borrarSQL = """
                DELETE FROM prueba WHERE texto = (?)
                """;
        PreparedStatement preparedBorrar = AppIndex.conexion.prepareStatement(borrarSQL);
        String valor = textoBorrar.getText();
        preparedBorrar.setString(1,valor);
        int n = preparedBorrar.executeUpdate();

        if (n>0){
            System.out.println("Registro insertado con exito");
            salida.setText("Registo insertado con exito");
            Statement stm = AppIndex.conexion.createStatement();
            String sql2 = """
                        SELECT * FROM prueba
                        """;
            ResultSet rs = stm.executeQuery(sql2);
            String temp="";
            while (rs.next()){
                temp += rs.getInt(1)+" "+rs.getString(2)+"\n" ;
            }
            contenido.setText(temp);
        } else {
        salida.setText(" ERROR CAMPO VACÍO ");
        System.out.println(" ERROR CAMPO VACÍO ");
    }
    }

}