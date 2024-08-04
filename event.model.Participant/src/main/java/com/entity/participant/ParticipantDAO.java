package com.entity.participant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ParticipantDAO {

    //SELECT
    public static void selectParticipants(Connection conn) throws SQLException {
        String sql = "SELECT * FROM participant";
        try(PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            while(rs.next()) {
                int id = rs.getInt(1);
                String email = rs.getString(2);
                String senha = rs.getString(3);

                System.out.printf("""
                        ID: %d | EMAIL: %s | SENHA: %s
                        """, id, email, senha);
            }

        }
    }

}
