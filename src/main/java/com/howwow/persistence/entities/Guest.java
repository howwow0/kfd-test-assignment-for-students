package com.howwow.persistence.entities;


import java.util.UUID;

public class Guest extends User {

    public Guest(UUID userId, String name, String email) {
        super(userId, name, email);
    }

    @Override
    public int getMaxBooks() {
        return 1;
    }

    @Override
    public int getBorrowDays() {
        return 7;
    }

    @Override
    public double getFinePerDay() {
        return 0.7;
    }
}
