package com.entity;

import java.time.LocalDate;
import java.util.Scanner;

public record Event(int id, int maxQuant, int regitered, LocalDate eventDate, String title, String description) {

    private static int defId(Scanner sc) {
        System.out.println("ID: ");
        return sc.nextInt();
    }

    private static int defMaxQuant(Scanner sc) {
        System.out.println("Max quantity: ");
        return sc.nextInt();
    }

    private static int defRegistered(Scanner sc) {
        System.out.println("# of registered participants: ");
        return sc.nextInt();
    }

    public static LocalDate defLocalDate(Scanner sc) {
        System.out.println("# Day of the event: ");
        int day = sc.nextInt();

        System.out.println("# Month of the event: ");
        int mon = sc.nextInt();

        System.out.println("Year of the event: ");
        int year = sc.nextInt();

        return LocalDate.of(year, mon, day);
    }

    private static String defTitle(Scanner sc) {
        System.out.println("Title of the event: ");
        return sc.next();
    }

    private static String defDesc(Scanner sc) {
        System.out.println("Description: ");
        return sc.next();
    }

    static Event createEvent(Scanner sc) {
        int id = defId(sc);
        int maxQuant = defMaxQuant(sc);
        int regs = defRegistered(sc);
        LocalDate ld = defLocalDate(sc);
        String title = defTitle(sc);
        String desc = defDesc(sc);

        return new Event(id, maxQuant, regs, ld, title, desc);
    }
}
