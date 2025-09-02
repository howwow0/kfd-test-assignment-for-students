package com.howwow.ui;

import com.howwow.service.BookService;
import com.howwow.ui.book.*;
import lombok.RequiredArgsConstructor;

/**
 * @author HowWow
 * @Date 02.09.2025
 */
@RequiredArgsConstructor
public class MenuCreator {
    private final BookService bookService;

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
        usersConfigurableMenuState.addMenuItem(1, new MenuItem("Add User"), () -> null);
        usersConfigurableMenuState.addMenuItem(2, new MenuItem("Remove User"), () -> null);
        usersConfigurableMenuState.addMenuItem(4, new MenuItem("Back to main menu"), configurableMenuState::runState);

        MenuState borrowingOperationsConfigurableMenuState = new ConfigurableMenuState();
        borrowingOperationsConfigurableMenuState.addMenuItem(1, new MenuItem("Borrow Book"), () -> null);
        borrowingOperationsConfigurableMenuState.addMenuItem(2, new MenuItem("Return Book"), () -> null);
        borrowingOperationsConfigurableMenuState.addMenuItem(4, new MenuItem("Back to main menu"), configurableMenuState::runState);

        configurableMenuState.addMenuItem(1, new MenuItem("Book Management"), booksConfigurableMenuState::runState);
        configurableMenuState.addMenuItem(2, new MenuItem("User Management"), usersConfigurableMenuState::runState);
        configurableMenuState.addMenuItem(3, new MenuItem("Borrowing Operations"), borrowingOperationsConfigurableMenuState::runState);
        configurableMenuState.addMenuItem(4, new MenuItem("Exit"), () -> null);

        return configurableMenuState;
    }
}
