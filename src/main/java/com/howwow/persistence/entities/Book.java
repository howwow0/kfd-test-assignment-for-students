package com.howwow.persistence.entities;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Book {
    @EqualsAndHashCode.Include
    private String isbn;
    private String title;
    private String author;
    private String genre;
    private Integer year;
}
