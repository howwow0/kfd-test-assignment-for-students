package com.howwow.persistence.repositories.impl;

import com.howwow.exception.BookAlreadyExistsException;
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
    public Book create(Book book) {
        if (books.containsKey(book.getIsbn())) {
            throw new BookAlreadyExistsException("Книга с заданным ISBN уже существует");
        }
        books.put(book.getIsbn(), book);
        return book;
    }

    @Override
    public List<Book> findByTitle(String title) {
        return books.values().stream()
                .filter(book -> book.getTitle().equals(title))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> findByAuthor(String author) {
        return books.values().stream()
                .filter(book -> book.getAuthor().equals(author))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        return Optional.ofNullable(books.get(isbn));
    }

    @Override
    public boolean removeByIsbn(String isbn) {
        return books.remove(isbn) != null;
    }
}
