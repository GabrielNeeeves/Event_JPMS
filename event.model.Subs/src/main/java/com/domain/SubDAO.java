package com.domain;

import com.entity.EventDAO;
import com.entity.participant.ParticipantDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class SubDAO {

    //SELECT
    public static void selectSubs(Connection conn) throws SQLException {
        List<Sub> subList = new ArrayList<>();
        String sql = "SELECT * FROM SUBSCRIPTION";
        try(PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {
            while(rs.next()) {
                int subId = rs.getInt(1);
                int eventId = rs.getInt(2);
                String partEmail = rs.getString(3);

                Sub newSub = new Sub(subId, eventId, partEmail);
                subList.add(newSub);
                Collections.sort(subList);
            }
            subList.forEach(System.out::println);
        }
    }

    //INSERT
    public static void insertSub(Connection conn, Scanner sc) throws SQLException {
        EventDAO.selectEvents(conn);
        System.out.println("# Type the Event's ID to subscribe: ");
        int eventId = sc.nextInt();

        int maxQuant = EventDAO.getMaxQuant(conn, eventId);
        int currentSub = EventDAO.getCurrentSubs(conn, eventId);

        if(currentSub < maxQuant) {

            ParticipantDAO.selectParticipants(conn);
            System.out.println("# Type the participant's Email to subscribe: ");
            String partEmail = sc.next();

            String sql = "INSERT INTO SUBSCRIPTION (event_id, participant_email) VALUES (?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, eventId);
                ps.setString(2, partEmail);

                int upt = ps.executeUpdate();
                if (upt != 0) System.out.println("Rows affected: " + upt);
            }
        } else {
            System.out.println("Crowded event");
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
