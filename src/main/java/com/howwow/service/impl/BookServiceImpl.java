package com.howwow.service.impl;

import com.howwow.persistence.entities.Book;
import com.howwow.persistence.repositories.BookRepository;
import com.howwow.service.BookService;
import com.howwow.ui.utils.ScannerUtils;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final Scanner scanner;

    @Override
    public void createBook() {
        System.out.println("=== Create New Book ===");

        Book book = new Book();
        book.setIsbn(ScannerUtils.getValidatedInput("Enter ISBN: ", "ISBN", scanner));
        book.setAuthor(ScannerUtils.getValidatedInput("Enter author: ", "Author", scanner));
        book.setTitle(ScannerUtils.getValidatedInput("Enter title: ", "Title", scanner));
        book.setYear(ScannerUtils.getValidatedInteger("Enter publication year: ", "Year", scanner));
        book.setGenre(ScannerUtils.getValidatedInput("Enter genre: ", "Genre", scanner));

        bookRepository.create(book);
        System.out.println("Book successfully saved!");
    }

    @Override
    public void removeBook() {
        System.out.println("=== Remove Book ===");

        String isbn = ScannerUtils.getValidatedInput("Enter ISBN of the book to remove: ", "ISBN", scanner);

        Optional<Book> bookToRemove = bookRepository.findByIsbn(isbn);
        if (bookToRemove.isEmpty()) {
            System.out.println("Book with ISBN " + isbn + " not found!");
            return;
        }
        Book book = bookToRemove.get();
        System.out.println("Found book: " + book.getTitle() + " by " + book.getAuthor());
        String confirmation = ScannerUtils.getValidatedInput("Are you sure you want to delete this book? (yes/no): ", "Confirmation", scanner);

        if (confirmation.equalsIgnoreCase("yes") || confirmation.equalsIgnoreCase("y")) {
            bookRepository.removeByIsbn(isbn);
            System.out.println("Book successfully removed!");
        } else {
            System.out.println("Book removal cancelled.");
        }
    }

    @Override
    public void findByTitle() {
        System.out.println("=== Find Book by Title ===");

        String title = ScannerUtils.getValidatedInput("Enter book title to search: ", "Title", scanner);
        List<Book> books = bookRepository.findByTitle(title);

        ScannerUtils.displaySearchResults(books, "title", title);
    }

    @Override
    public void findByAuthor() {
        System.out.println("=== Find Book by Author ===");

        String author = ScannerUtils.getValidatedInput("Enter author name to search: ", "Author", scanner);
        List<Book> books = bookRepository.findByAuthor(author);

        ScannerUtils.displaySearchResults(books, "author", author);
    }

    @Override
    public void findByIsbn() {
        System.out.println("=== Find Book by ISBN ===");

        String isbn = ScannerUtils.getValidatedInput("Enter ISBN to search: ", "ISBN", scanner);

        Optional<Book> bookOptional = bookRepository.findByIsbn(isbn);

        bookOptional.ifPresentOrElse(
                book -> {
                    System.out.println("\nBook found:");
                    ScannerUtils.displayBookDetails(book);
                },
                () -> System.out.println("No book found with ISBN: " + isbn)
        );
    }

}
