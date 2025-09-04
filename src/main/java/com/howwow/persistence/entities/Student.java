package com.howwow.persistence.entities;


import lombok.NoArgsConstructor;

@NoArgsConstructor

public class Student extends User {

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
