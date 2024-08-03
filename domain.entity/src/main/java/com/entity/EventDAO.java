package com.entity;

import java.sql.*;
import java.time.LocalDate;

public class EventDAO {

    //SELECT
    public static void getEvents(Connection conn) throws SQLException {
        String sql = "SELECT * FROM EVENT";
        try(PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            while(rs.next()) {
                int id = rs.getInt(1);
                int maxQuant = rs.getInt(2);
                int registered = rs.getInt(3);
//                Date date = rs.getDate(3);
//                LocalDate eventDate = date.toLocalDate();
                LocalDate eventDate = rs.getObject(4, LocalDate.class);
                String title = rs.getString(5);
                String desc = rs.getString(6);

                System.out.printf("""
                        %d | %d | %d | %s | %s | %s
                        """, id, maxQuant, registered ,eventDate, title, desc);
            }

        }
    }

}
