package com.howwow.ui.utils;

import com.howwow.persistence.entities.Book;

import java.util.List;
import java.util.Scanner;

public class ScannerUtils {
    public static String getValidatedInput(String prompt, String fieldName, Scanner scanner) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println(fieldName + " must not be empty!");
            }
        } while (input.isEmpty());
        return input;
    }

    public static int getValidatedInteger(String prompt, String fieldName, Scanner scanner) {
        int input = 0;
        boolean valid = false;

        do {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                scanner.nextLine();
                valid = true;
            } else {
                System.out.println(fieldName + " must be a valid integer number!");
                scanner.next();
            }
        } while (!valid);

        return input;
    }

    public static void displaySearchResults(List<Book> books, String searchType, String searchValue) {
        if (books == null || books.isEmpty()) {
            System.out.println("No books found with " + searchType + ": " + searchValue);
        } else {
            System.out.println("Found " + books.size() + " book(s) with " + searchType + ": " + searchValue);
            System.out.println("----------------------------------------");

            for (int i = 0; i < books.size(); i++) {
                Book book = books.get(i);
                System.out.println((i + 1) + ". " + book.getTitle() + " by " + book.getAuthor() +
                        " (ISBN: " + book.getIsbn() + ", Year: " + book.getYear() + ")");
            }
        }
    }

    public static void displayBookDetails(Book book) {
        System.out.println("\nBook found:");
        System.out.println("Title: " + book.getTitle());
        System.out.println("Author: " + book.getAuthor());
        System.out.println("ISBN: " + book.getIsbn());
        System.out.println("Publication Year: " + book.getYear());
        System.out.println("Genre: " + book.getGenre());
    }
}
