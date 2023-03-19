package com.example.ejemplobuenobbdd;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class AppController implements Initializable {
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
    private Button botonModificar;
    @FXML
    private TextField textoModificar;

    @FXML
    protected void onInsertButtonClick() throws SQLException {

        String valor = entrada.getText();
        String sqlCheckCount = """
                SELECT COUNT(*) FROM tarea;
                """;
        PreparedStatement preparedCheckCount = AppIndex.conexion.prepareStatement(sqlCheckCount);
        if (preparedCheckCount.equals("1")){
            salida.setText("DATO REPETIDO");
        }
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
        entrada.clear();
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
            System.out.println("Registro BORRADO con exito");
            salida.setText("Registo BORRADO con exito");
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
        salida.setText("ERROR NO EXISTE ESA COLUMNA");
        System.out.println("ERROR NO EXISTE ESA COLUMNA");
    }
        textoBorrar.clear();
    }

    @FXML
    protected void onModifyButton() throws SQLException {

        botonModificar.setVisible(false);
        textoModificar.setVisible(false);


        String modificarSQL = """
                DELETE FROM prueba WHERE texto = (?)
                """;
        String modificarSQL2 = """
                    INSERT INTO prueba (texto) VALUES (?)
                    """;
        PreparedStatement preparedModificar1 = AppIndex.conexion.prepareStatement(modificarSQL);
        String valor = textoModificar.getText();
        preparedModificar1.setString(1,valor);
        int n = preparedModificar1.executeUpdate();

        PreparedStatement preparedModificar2 = AppIndex.conexion.prepareStatement(modificarSQL2);
        String variable = textoModificar.getText();
        preparedModificar2.setString(1,valor);
        int n1 = preparedModificar2.executeUpdate();

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
            salida.setText("ERROR NO EXISTE ESA COLUMNA");
            System.out.println("ERROR NO EXISTE ESA COLUMNA");
        }
        String sqlCheckEspacio = """
                SELECT COUNT(*) FROM pruebas
                """;
        PreparedStatement pstmtChech = AppIndex.conexion.prepareStatement(sqlCheckEspacio);

        textoBorrar.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {


        Statement stm2 = AppIndex.conexion.createStatement();
        String sql2 = """
                        SELECT * FROM prueba
                        """;
        ResultSet rs = stm2.executeQuery(sql2);
        String temp="";
        while (rs.next()){
            temp += rs.getInt(1)+" "+rs.getString(2)+"\n" ;
        }
        contenido.setText(temp);

    }catch (Exception ñ){
            System.out.println(ñ);
        }
    }
}