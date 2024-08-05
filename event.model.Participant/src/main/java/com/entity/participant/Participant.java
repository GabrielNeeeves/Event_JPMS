package com.entity.participant;

import java.util.Scanner;

public record Participant(int id, String email, String senha) {

    private static int defId(Scanner sc) {
        System.out.println("ID: ");
        return sc.nextInt();
    }

    private static String defEmail(Scanner sc) {
        System.out.println("Type your email: ");
        return sc.next();
    }

    private static String defPass(Scanner sc) {
        System.out.println("Type your password: ");
        return sc.next();
    }

    public static Participant createPart(Scanner sc) {
        int id = defId(sc);
        String email = defEmail(sc);
        String pass = defPass(sc);
        return new Participant(id, email, pass);
    }

}
