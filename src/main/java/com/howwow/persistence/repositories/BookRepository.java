package com.howwow.persistence.repositories;

import com.howwow.persistence.entities.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Book create(Book book);
    List<Book> findByTitle(String title);
    List<Book> findByAuthor(String author);
    Optional<Book> findByIsbn(String isbn);
    boolean removeByIsbn(String isbn);
}
