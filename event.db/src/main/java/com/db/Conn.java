package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conn {

    private static final String URL = "jdbc:postregsql://localhost:5432/postgres";
    private static final String PASS = "rootadmin";
    private static final String USERNAME = "fromtechadmin";

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, PASS, USERNAME);
        } catch(SQLException ex) {
            System.out.println("Erro de conexão: "+ex.getMessage());
        }
        return conn;
    }


}
