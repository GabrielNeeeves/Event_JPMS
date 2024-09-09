package com.entity;

import com.db.Conn;
import com.sun.java.accessibility.util.EventID;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static com.entity.Event.defLocalDate;

public class EventDAO {

    //SELECT
    public static void selectEvents(Connection conn) throws SQLException {
        List<Event> eventList = new ArrayList<>();
        String sql = "SELECT * FROM EVENT";
        try(PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            while(rs.next()) {
                int id = rs.getInt(1);
                int maxQuant = rs.getInt(2);
                LocalDate eventDate = rs.getObject(3, LocalDate.class);
                String title = rs.getString(4);
                String desc = rs.getString(5);

                eventList.add(new Event(id, maxQuant, eventDate, title, desc));
                Collections.sort(eventList);
            }
            eventList.forEach(System.out::println);

        }
    }

    //INSERT
    public static void insertEvents(Connection conn, Scanner sc) throws SQLException {
        Event newEvent = Event.createEvent(sc);

        String sql = "INSERT INTO EVENT (id, maxQuant, eventDate, title, description) VALUES (?, ?, ?, ?, ?)";
        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, newEvent.id());
            ps.setInt(2, newEvent.maxQuant());
            ps.setObject(3, newEvent.eventDate());
            ps.setString(4, newEvent.title());
            ps.setString(5, newEvent.description());

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

        System.out.println("Event date: ");
        LocalDate newLocalDate = defLocalDate(sc);

        System.out.println("New Title: ");
        String newTitle = sc.next();

        System.out.println("New Description: ");
        String newDesc = sc.next();

        String sql = ("""
                UPDATE event SET
                maxQuant = ?,
                eventDate = ?,
                title = ?,
                description = ?
                WHERE ID = ?
                """);

        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, newMaxQuant);
            ps.setObject(2, newLocalDate); //LocalDate
            ps.setString(3, newTitle);
            ps.setString(4, newDesc);
            ps.setInt(5, eventId);

            int upt = ps.executeUpdate();
            System.out.println("Rows updated: "+upt);
        }
    }

    //GET maxQuant from Event Table
    public static int getMaxQuant(Connection conn, int eventId) throws SQLException {
        String sql = "SELECT maxQuant FROM EVENT WHERE id = ?";
        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, eventId);

            try(ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    return rs.getInt(1);
                }
            }
            return 0;
        }
    }

    //GET current subs for that Event
    public static int getCurrentSubs(Connection conn, int eventId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM SUBSCRIPTION WHERE event_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, eventId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return 0;
    }
}
