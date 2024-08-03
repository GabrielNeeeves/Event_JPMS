package com.entity;

import java.time.LocalDate;

public record Event(int id, int maxQuant, int regitered, LocalDate eventDate, String title, String description) {
}
