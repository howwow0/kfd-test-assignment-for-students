package com.howwow.persistence.entities;

import java.util.UUID;

public class Student extends User {
    public Student(UUID userId, String name, String email) {
        super(userId, name, email);
    }

    @Override
    public int getMaxBooks() {
        return 3;
    }

    @Override
    public int getBorrowDays() {
        return 14;
    }

    @Override
    public double getFinePerDay() {
        return 0.5;
    }
}
