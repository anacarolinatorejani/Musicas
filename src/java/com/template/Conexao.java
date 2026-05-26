package com.template;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String conexaoURL = "jdbc:postgresql://localhost:5432/Musicas";
    private static final String usuario = "postgres";
    private static final String senha = "postgres";

    public Connection conectaBD() {
        try {
            return DriverManager.getConnection(conexaoURL, usuario, senha);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar ao banco de dados: " + e.getMessage(), e);
        }
    }
}