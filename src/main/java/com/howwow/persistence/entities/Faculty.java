package com.howwow.persistence.entities;

import java.util.UUID;

public class Faculty extends User{
    public Faculty(UUID userId, String name, String email) {
        super(userId, name, email);
    }

    @Override
    public int getMaxBooks() {
        return 10;
    }

    @Override
    public int getBorrowDays() {
        return 30;
    }

    @Override
    public double getFinePerDay() {
        return 0.2;
    }
}
