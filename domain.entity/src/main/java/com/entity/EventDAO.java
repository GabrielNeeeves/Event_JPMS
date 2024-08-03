package com.entity;

import java.sql.*;
import java.time.LocalDate;

public class EventDAO {

    //SELECT
    public static void getEvents(Connection conn) throws SQLException {
        String sql = "SELCT * FROM EVENT";
        try(PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            while(rs.next()) {
                int id = rs.getInt(1);
                int maxQuant = rs.getInt(2);
                Date date = rs.getDate(3);
                LocalDate eventDate = date.toLocalDate();
                String title = rs.getString(4);
                String desc = rs.getString(5);

                System.out.printf("""
                        %d | %d | %s | %s | %s
                        """, id, maxQuant, eventDate, title, desc);
            }

        }
    }

}
