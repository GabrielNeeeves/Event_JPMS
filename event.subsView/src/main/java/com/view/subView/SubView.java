package com.view.subView;

import com.domain.SubDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class SubView {

    //DISPLAY MENU
    public void showMenu(Connection conn, Scanner sc) throws SQLException {
        boolean exit = false;
        while(!exit) {
            System.out.println("1. Display registered participants");
            System.out.println("2. Subscribe participant");
            System.out.println("3. Delete registered participant by ID");
            System.out.println("9. Previous");
            int option = sc.nextInt();

            switch (option) {
                case 1: SubDAO.selectSubs(conn); break;
                case 2: SubDAO.insertSub(conn, sc); break;
                case 3: SubDAO.deleteSubById(conn, sc); break;
                case 9: exit = true; break;
                default: System.out.println("Invalid option. Try again");
            }

        }
    }

}
