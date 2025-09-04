package com.howwow.persistence.entities;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class User {
    @EqualsAndHashCode.Include
    protected String userId;
    protected String name;
    protected String email;

    public abstract int getMaxBooks();

    public abstract int getBorrowDays();

    public abstract double getFinePerDay();
}
