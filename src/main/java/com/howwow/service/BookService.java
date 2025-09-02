package com.howwow.service;

/**
 * Псевдо - сервисный слой. Т.к тут сразу работа с CLI
 */
public interface BookService {
    void createBook();

    void removeBook();

    void findByTitle();

    void findByAuthor();

    void findByIsbn();
}
