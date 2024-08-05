package com.participantView;

import com.entity.participant.ParticipantDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class ParticipantView {

    //DISPLAY MENU
    public static void showMenu(Connection conn, Scanner sc) throws SQLException {
        boolean exit = false;
        while(!exit) {
            System.out.println("1. Display participants");
            System.out.println("2. Create new participant");
            System.out.println("3. Delete a participant");
            System.out.println("4. Update a participant");
            System.out.println("9. Previous");
            int option = sc.nextInt();

            switch (option) {
                case 1: ParticipantDAO.selectParticipants(conn);break;
                case 2: ParticipantDAO.insertPart(conn, sc); break;
                case 3: ParticipantDAO.deletePart(conn, sc); break;
                case 4: ParticipantDAO.updatePart(conn, sc); break;
                case 9: exit = true; break;
                default: System.out.println("Invalid option. Try again");
            }

        }
    }

}
