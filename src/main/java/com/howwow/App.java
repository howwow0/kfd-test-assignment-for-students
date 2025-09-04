package com.howwow;

import com.howwow.persistence.repositories.BookRepository;
import com.howwow.persistence.repositories.BorrowingRecordRepository;
import com.howwow.persistence.repositories.UserRepository;
import com.howwow.persistence.repositories.impl.BookRepositoryImpl;
import com.howwow.persistence.repositories.impl.BorrowingRecordRepositoryImpl;
import com.howwow.persistence.repositories.impl.UserRepositoryImpl;
import com.howwow.service.BookService;
import com.howwow.service.BorrowingRecordService;
import com.howwow.service.UserService;
import com.howwow.service.impl.BookServiceImpl;
import com.howwow.service.impl.BorrowingRecordServiceImpl;
import com.howwow.service.impl.UserServiceImpl;
import com.howwow.ui.MenuCreator;
import com.howwow.ui.State;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        BookRepository bookRepository = new BookRepositoryImpl(new HashMap<>());
        UserRepository userRepository = new UserRepositoryImpl(new HashMap<>());
        BorrowingRecordRepository borrowingRecordRepository = new BorrowingRecordRepositoryImpl(new ArrayList<>());
        State runningState = getRunningState(bookRepository, userRepository, borrowingRecordRepository);
        while (runningState != null) {
            runningState = runningState.runState();
        }
    }

    private static State getRunningState(BookRepository bookRepository, UserRepository userRepository, BorrowingRecordRepository borrowingRecordRepository) {
        Scanner scanner = new Scanner(System.in);
        BookService bookService = new BookServiceImpl(bookRepository, scanner);
        UserService userService = new UserServiceImpl(userRepository, scanner);
        BorrowingRecordService borrowingRecordService = new BorrowingRecordServiceImpl(borrowingRecordRepository, userRepository, bookRepository, scanner);
        MenuCreator menuCreator = new MenuCreator(bookService, userService, borrowingRecordService);
        return menuCreator.createMenu();
    }

}
