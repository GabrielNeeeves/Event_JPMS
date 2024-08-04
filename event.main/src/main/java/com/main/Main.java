package com.main;

import com.db.Conn;
import com.entity.EventDAO;
import com.entity.participant.ParticipantDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try(Scanner sc = new Scanner(System.in);
            Connection conn = Conn.connect()) {

            try {
                System.out.println("EVENTS: ");
                EventDAO.selectEvents(conn);

                System.out.println("PARTS");
                ParticipantDAO.selectParticipants(conn);

            } catch(SQLException ex) {
                System.out.println("Erro de consulta: "+ex.getMessage());
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
