package com.howwow.persistence.entities;


import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Guest extends User {

    @Override
    public int getMaxBooks() {
        return 1;
    }

    @Override
    public int getBorrowDays() {
        return 7;
    }

}
