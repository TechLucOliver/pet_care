package br.com.clinicaveterinaria.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {

    private static final String NOME_BD = "clinicaveterinariadb";
    
    private static final String HOST = "localhost";
    
    private static final String PORTA = "5432";
    
    private static final String USUARIO = "postgres";
    
    private static final String SENHA = "postgres"; 

    private static final String URL = "jdbc:postgresql://" + HOST + ":" + PORTA + "/" + NOME_BD;

    public static Connection getConexao() throws SQLException {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver JDBC do PostgreSQL não encontrado!");
            throw new SQLException("Driver não encontrado", e);
        }
        
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}