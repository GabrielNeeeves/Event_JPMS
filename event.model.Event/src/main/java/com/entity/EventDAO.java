package com.entity;

import com.db.Conn;
import com.sun.java.accessibility.util.EventID;

import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

import static com.entity.Event.defLocalDate;

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
                        ID: %d | maxQuantity: %d | Registered: %d | eventDate: %s | Title: %s | Description: %s
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

    //DELETE
    public static void deleteById(Connection conn, Scanner sc) throws SQLException {
        System.out.println("# Type the event ID to delete it: ");
        selectEvents(conn);
        int option = sc.nextInt();

        String sql = "DELETE FROM event WHERE ID = ?";
        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, option);
            int upt = ps.executeUpdate();
            System.out.println("Rows deleted: "+upt);
        }
    }

    //UPDATE
    public static void updateById(Connection conn, Scanner sc) throws SQLException {
        selectEvents(conn);
        System.out.println("# Type the Event ID to modify it: ");
        int eventId = sc.nextInt();

        System.out.println("Max Quantity: ");
        int newMaxQuant = sc.nextInt();

        System.out.println("# Registered: ");
        int newRegd = sc.nextInt();

        System.out.println("Event date: ");
        LocalDate newLocalDate = defLocalDate(sc);

        System.out.println("New Title: ");
        String newTitle = sc.next();

        System.out.println("New Description: ");
        String newDesc = sc.next();

        String sql = ("""
                UPDATE event SET
                maxQuant = ?,
                registered = ?,
                eventDate = ?,
                title = ?,
                description = ?
                WHERE ID = ?
                """);

        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, newMaxQuant);
            ps.setInt(2, newRegd);
            ps.setObject(3, newLocalDate); //LocalDate
            ps.setString(4, newTitle);
            ps.setString(5, newDesc);
            ps.setInt(6, eventId);

            int upt = ps.executeUpdate();
            System.out.println("Rows updated: "+upt);
        }
    }
}
