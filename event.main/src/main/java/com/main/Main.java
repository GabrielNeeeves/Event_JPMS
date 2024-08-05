package com.main;

import com.db.Conn;
import com.entity.EventDAO;
import com.eventView.EventView;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        EventView ev = new EventView();

        try(Scanner sc = new Scanner(System.in);
            Connection conn = Conn.connect()) {

            try {
                ev.showMenu(conn, sc);

            } catch(SQLException ex) {
                System.out.println("Erro de consulta: "+ex.getMessage());
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
