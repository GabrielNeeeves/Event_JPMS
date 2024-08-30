package com.main;

import com.db.Conn;
import com.eventView.EventView;
import com.participantView.ParticipantView;
import com.view.subView.SubView;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        EventView ev = new EventView();
        ParticipantView pv = new ParticipantView();
        SubView sb = new SubView();

        try(Scanner sc = new Scanner(System.in);
            Connection conn = Conn.connect()) {

            try {
                boolean exit = false;
                while(!exit) {
                    System.out.println("1. Event");
                    System.out.println("2. Participant");
                    System.out.println("3. Subscription");
                    System.out.println("9. Exit");
                    int opt = sc.nextInt();
                    switch (opt) {
                        case 1: ev.showMenu(conn, sc); break;
                        case 2: pv.showMenu(conn, sc); break;
                        case 3: sb.showMenu(conn, sc); break;
                        case 9: exit = true; break;
                        default: System.out.println("Try Again");
                    }
                }

            } catch(SQLException ex) {
                ex.printStackTrace();
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
