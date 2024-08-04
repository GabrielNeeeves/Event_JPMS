package com.entity;

import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

public class EventDAO {

    //SELECT
    public static void selectEvents(Connection conn) throws SQLException {
        String sql = "SELECT * FROM EVENT";
        try(PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            while(rs.next()) {
                int id = rs.getInt(1);
                int maxQuant = rs.getInt(2);
                int registered = rs.getInt(3);
                LocalDate eventDate = rs.getObject(4, LocalDate.class);
                String title = rs.getString(5);
                String desc = rs.getString(6);

                System.out.printf("""
                        %d | %d | %d | %s | %s | %s
                        """, id, maxQuant, registered ,eventDate, title, desc);
            }

        }
    }

    //INSERT
    public static void insertEvents(Connection conn, Scanner sc) throws SQLException {

        Event newEvent = Event.createEvent(sc);

        String sql = "INSERT INTO EVENT (id, maxQuant, registered, eventDate, title, description) VALUES (?, ?, ?, ?, ?, ?)";
        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, newEvent.id());
            ps.setInt(2, newEvent.maxQuant());
            ps.setInt(3, newEvent.regitered());
            ps.setObject(4, newEvent.eventDate());
            ps.setString(5, newEvent.title());
            ps.setString(6, newEvent.description());

            int upt = ps.executeUpdate();
            System.out.println("Rows inserted: "+upt);
        }
    }

}
