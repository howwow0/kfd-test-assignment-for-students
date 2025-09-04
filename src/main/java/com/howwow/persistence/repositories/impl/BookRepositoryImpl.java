package com.howwow.persistence.repositories.impl;

import com.howwow.exception.BookCreationException;
import com.howwow.persistence.entities.Book;
import com.howwow.persistence.repositories.BookRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository {
    private final Map<String, Book> books;

    @Override
    public void create(Book book) {
        if (books.containsKey(book.getIsbn())) {
            throw new BookCreationException("Book with ISBN " + book.getIsbn() + " already exists");
        }
        books.put(book.getIsbn(), book);
    }

    @Override
    public List<Book> findByTitle(String title) {
        return books.values().stream()
                .filter(book -> book.getTitle().contains(title))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> findByAuthor(String author) {
        return books.values().stream()
                .filter(book -> book.getAuthor().contains(author))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        return Optional.ofNullable(books.get(isbn));
    }

    @Override
    public void removeByIsbn(String isbn) {
        books.remove(isbn);
    }
}
