package com.howwow.persistence.entities;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Faculty extends User{

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
