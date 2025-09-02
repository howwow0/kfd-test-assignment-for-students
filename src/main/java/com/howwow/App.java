package com.howwow;

import com.howwow.persistence.repositories.BookRepository;
import com.howwow.persistence.repositories.impl.BookRepositoryImpl;
import com.howwow.service.BookService;
import com.howwow.service.impl.BookServiceImpl;
import com.howwow.ui.*;

import java.util.HashMap;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        BookRepository bookRepository = new BookRepositoryImpl(new HashMap<>());
        Scanner scanner = new Scanner(System.in);
        BookService bookService = new BookServiceImpl(bookRepository, scanner);

        MenuCreator menuCreator = new MenuCreator(bookService);
        State runningState = menuCreator.createMenu();
        while (runningState != null) {
            runningState = runningState.runState();
        }
    }
}
