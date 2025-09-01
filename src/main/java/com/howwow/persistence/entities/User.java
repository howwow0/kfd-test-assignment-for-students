package com.howwow.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public abstract class User {
    protected UUID userId;
    protected String name;
    protected String email;

    public abstract int getMaxBooks();

    public abstract int getBorrowDays();

    public abstract double getFinePerDay();
}
