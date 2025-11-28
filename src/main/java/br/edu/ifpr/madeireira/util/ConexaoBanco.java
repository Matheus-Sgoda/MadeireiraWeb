package br.edu.ifpr.madeireira.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBanco {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/madeireira_db?useTimezone=true&serverTimezone=UTC&useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConexao() {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Erro ao conectar no banco: ", e);
        }
    }

    public static void main(String[] args) {
        try {
            Connection con = getConexao();
            System.out.println("Banco conectado com sucesso!");
            con.close();
        } catch (Exception e) {
            System.out.println("Falha na conexão.");
            e.printStackTrace();
        }
    }
}
