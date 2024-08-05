package com.eventView;

import com.entity.EventDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class EventView {

    //displayMenu
    public void showMenu(Connection conn, Scanner sc) throws SQLException {

        boolean exit = false;

        while(!exit) {
            System.out.println("1. Show events");
            System.out.println("2. Create new Event");
            System.out.println("3. Delete Event");
            System.out.println("4. Update Event");
            System.out.println("9. Exit");
            int option = sc.nextInt();

            switch (option) {
                case 1 : EventDAO.selectEvents(conn); break;
                case 2 : EventDAO.insertEvents(conn, sc); break;
                case 3 : EventDAO.deleteById(conn, sc); break;
                case 4 : EventDAO.updateById(conn, sc); break;
                case 9 : exit = true; break;
                default : System.out.println("Invalid option. Try again");
            }
        }
    }

}
