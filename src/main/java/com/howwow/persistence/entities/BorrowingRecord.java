package com.howwow.persistence.entities;

import lombok.*;

import java.time.Instant;

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BorrowingRecord {
    private String isbn;
    private String userId;
    private Instant dueTime;
}
