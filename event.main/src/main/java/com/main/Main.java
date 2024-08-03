package com.main;

import com.db.Conn;
import com.entity.EventDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try(Scanner sc = new Scanner(System.in);
            Connection conn = Conn.connect()) {

            try {
                EventDAO.getEvents(conn);
            } catch(SQLException ex) {
                System.out.println("Erro de consulta: "+ex.getMessage());
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
