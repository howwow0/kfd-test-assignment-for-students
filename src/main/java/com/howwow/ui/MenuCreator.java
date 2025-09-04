package com.howwow.ui;

import com.howwow.service.BookService;
import com.howwow.service.BorrowingRecordService;
import com.howwow.service.UserService;
import com.howwow.ui.book.*;
import com.howwow.ui.borrowingrecord.BorrowingRecordBorrowState;
import com.howwow.ui.borrowingrecord.BorrowingRecordOverdueState;
import com.howwow.ui.borrowingrecord.BorrowingRecordReturnState;
import com.howwow.ui.user.UserCreationState;
import com.howwow.ui.user.UserDeletionState;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MenuCreator {
    private final BookService bookService;
    private final UserService userService;
    private final BorrowingRecordService borrowingRecordService;

    /**
     * Фабричный метод создания дефолтного меню.
     *
     * @return State начальная точка запуска для меню
     */
    public State createMenu() {
        MenuState configurableMenuState = new ConfigurableMenuState();
        MenuState booksConfigurableMenuState = new ConfigurableMenuState();

        MenuState searchBooksConfigurableMenuState = new ConfigurableMenuState();
        searchBooksConfigurableMenuState.addMenuItem(1, new MenuItem("Search by Title"), () -> new BookSearchByTitleState(bookService, booksConfigurableMenuState));
        searchBooksConfigurableMenuState.addMenuItem(2, new MenuItem("Search by Author"), () -> new BookSearchByAuthorState(bookService, booksConfigurableMenuState));
        searchBooksConfigurableMenuState.addMenuItem(3, new MenuItem("Search by Isbn"), () -> new BookSearchByIsbnState(bookService, booksConfigurableMenuState));
        searchBooksConfigurableMenuState.addMenuItem(4, new MenuItem("Back to Book Management"), booksConfigurableMenuState::runState);

        booksConfigurableMenuState.addMenuItem(1, new MenuItem("Add Book"), () -> new BookCreationState(bookService, booksConfigurableMenuState));
        booksConfigurableMenuState.addMenuItem(2, new MenuItem("Remove Book"), () -> new BookDeletionState(bookService, booksConfigurableMenuState));
        booksConfigurableMenuState.addMenuItem(3, new MenuItem("Search Books"), searchBooksConfigurableMenuState::runState);
        booksConfigurableMenuState.addMenuItem(4, new MenuItem("Back to main menu"), configurableMenuState::runState);


        MenuState usersConfigurableMenuState = new ConfigurableMenuState();
        usersConfigurableMenuState.addMenuItem(1, new MenuItem("Add User"), () -> new UserCreationState(userService, usersConfigurableMenuState));
        usersConfigurableMenuState.addMenuItem(2, new MenuItem("Remove User"), () -> new UserDeletionState(userService, usersConfigurableMenuState));
        usersConfigurableMenuState.addMenuItem(3, new MenuItem("Back to main menu"), configurableMenuState::runState);

        MenuState borrowingOperationsConfigurableMenuState = new ConfigurableMenuState();
        borrowingOperationsConfigurableMenuState.addMenuItem(1, new MenuItem("Borrow Book"), () -> new BorrowingRecordBorrowState(borrowingRecordService, borrowingOperationsConfigurableMenuState));
        borrowingOperationsConfigurableMenuState.addMenuItem(2, new MenuItem("Return Book"), () -> new BorrowingRecordReturnState(borrowingRecordService, borrowingOperationsConfigurableMenuState));
        borrowingOperationsConfigurableMenuState.addMenuItem(3, new MenuItem("Overdue Books"), () -> new BorrowingRecordOverdueState(borrowingRecordService, borrowingOperationsConfigurableMenuState));
        borrowingOperationsConfigurableMenuState.addMenuItem(4, new MenuItem("Back to main menu"), configurableMenuState::runState);

        configurableMenuState.addMenuItem(1, new MenuItem("Book Management"), booksConfigurableMenuState::runState);
        configurableMenuState.addMenuItem(2, new MenuItem("User Management"), usersConfigurableMenuState::runState);
        configurableMenuState.addMenuItem(3, new MenuItem("Borrowing Operations"), borrowingOperationsConfigurableMenuState::runState);
        configurableMenuState.addMenuItem(4, new MenuItem("Exit"), () -> null);

        return configurableMenuState;
    }
}
