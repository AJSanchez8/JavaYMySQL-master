package com.example.ejemplobuenobbdd;

import java.sql.Connection;

public class CierraConexion extends Thread{

    @Override
    public void run(){
        Connection refConAbierta = null;
        try{
            refConAbierta = MiConexion.abrirConexion();
            refConAbierta.close();
            System.out.println("Cerrando conexion a aplicacion BD");
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
