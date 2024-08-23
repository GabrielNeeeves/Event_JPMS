package com.domain;

import com.entity.EventDAO;
import com.entity.participant.ParticipantDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Scanner;

public class SubDAO {

    //SELECT
    public static void selectSubs(Connection conn) throws SQLException {
        String sql = "SELECT * FROM SUBSCRIPTION";
        try(PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {
            while(rs.next()) {
                int subId = rs.getInt(1);
                int eventId = rs.getInt(2);
                String partEmail = rs.getString(3);
                System.out.printf("""
                        Sub's id: %d | Event's id: %d | Participant's Email: %s \n""",subId, eventId, partEmail);
            }
        }
    }

    //INSERT
    public static void insertSub(Connection conn, Scanner sc) throws SQLException {
        EventDAO.selectEvents(conn);
        System.out.println("# Type the Event's ID to subscribe: ");
        int eventId = sc.nextInt();

        ParticipantDAO.selectParticipants(conn);
        System.out.println("# Type the participant's Email to subscribe: ");
        String partEmail = sc.next();

        String sql = "INSERT INTO SUBSCRIPTION (EVENT_ID, participant_email) VALUES (?, ?)";
        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, eventId);
            ps.setString(2,partEmail);

            int upt = ps.executeUpdate();
            if(upt != 0 ) System.out.println("Rows affected: "+upt);
        }
    }

    //DELETE by id
    public static void deleteSubById(Connection conn, Scanner sc) throws SQLException {
        selectSubs(conn);
        System.out.println("# Type the ID to delete it: ");
        int eventId = sc.nextInt();

        String sql = "DELETE FROM SUBSCRIPTION WHERE sub_id = ?";
        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, eventId);

            int upt = ps.executeUpdate();
            if(upt != 0 ) System.out.println("Rows affected: "+upt);
        }
    }

}
