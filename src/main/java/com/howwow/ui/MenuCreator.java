package com.howwow.ui;

/**
 * @author HowWow
 * @Date 02.09.2025
 */
public class MenuCreator {
    /**
     * Фабричный метод создания дефолтного меню.
     *
     * @return State начальная точка запуска для меню
     */
    public State createMenu() {
        MenuState configurableMenuState = new ConfigurableMenuState();
        MenuState booksConfigurableMenuState = new ConfigurableMenuState();
        booksConfigurableMenuState.addMenuItem(1, new MenuItem("Add Book"), () -> null);
        booksConfigurableMenuState.addMenuItem(2, new MenuItem("Remove Book"), () -> null);
        booksConfigurableMenuState.addMenuItem(3, new MenuItem("Search Books"), () -> null);
        booksConfigurableMenuState.addMenuItem(4, new MenuItem("Back to main menu"), configurableMenuState::runState);


        MenuState usersConfigurableMenuState = new ConfigurableMenuState();
        usersConfigurableMenuState.addMenuItem(1, new MenuItem("Add User"), () -> null);
        usersConfigurableMenuState.addMenuItem(2, new MenuItem("Remove User"), () -> null);
        usersConfigurableMenuState.addMenuItem(3, new MenuItem("Search Users"), () -> null);
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
