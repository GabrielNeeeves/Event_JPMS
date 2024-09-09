package com.entity.participant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class ParticipantDAO {

    //SELECT
    public static void selectParticipants(Connection conn) throws SQLException {
        List<Participant> partList = new ArrayList<>();
        String sql = "SELECT * FROM participant";
        try(PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            while(rs.next()) {
                int id = rs.getInt(1);
                String email = rs.getString(2);
                String senha = rs.getString(3);

                var newPart = new Participant(id, email, senha);
                partList.add(newPart);
                Collections.sort(partList);
            }
            partList.forEach(System.out::println);

        }
    }

    //INSERT
    public static void insertPart(Connection conn, Scanner sc) throws SQLException {
        Participant newPart = Participant.createPart(sc);

        String sql = "INSERT INTO participant (id, email, senha) VALUES (?, ?, ?)";
        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, newPart.id());
            ps.setString(2, newPart.email());
            ps.setString(3, newPart.pass());

            int upt = ps.executeUpdate();
            System.out.println("Rows affected: "+upt);
        }
    }

    //DELETE
    public static void deletePart(Connection conn, Scanner sc) throws SQLException {
        selectParticipants(conn);
        System.out.println("# Type Participant ID to be deleted: ");
        int partId = sc.nextInt();

        String sql = "DELETE FROM participant WHERE id = ?";
        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, partId);

            int upt = ps.executeUpdate();
            System.out.println("Rows affected: "+upt);
        }
    }

    //UPDATE
    public static void updatePart(Connection conn, Scanner sc) throws SQLException {
        selectParticipants(conn);
        System.out.println("# Type Participant ID to be updated");
        int partId = sc.nextInt();

        System.out.println("Insert a new Email: ");
        String newEmail = sc.next();

        System.out.println("Create a new Password: ");
        String newPass = sc.next();

        String sql = """
                UPDATE participant SET
                email = ?,
                senha = ?
                WHERE id = ?
                """;
        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, newEmail);
            ps.setString(2, newPass);
            ps.setInt(3, partId);

            int updt = ps.executeUpdate();
            System.out.println("Rows affected: "+updt);

        }
    }

}
