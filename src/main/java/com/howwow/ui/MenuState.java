package com.howwow.ui;


import java.util.AbstractMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Supplier;

/**
 * Базовый класс машины состояний меню
 */
public abstract class MenuState implements State {

    protected abstract Map<Integer, MenuItem> menus();

    protected abstract State nextState(Map.Entry<Integer, MenuItem> selectedMenu);

    protected Map.Entry<Integer, MenuItem> readOption() {
        System.out.println("Please, select option: ");
        showMenu();

        Scanner scanner = new Scanner(System.in);
        try {
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Input cannot be blank! Please enter a number.");
                return readOption();
            }

            int answer = Integer.parseInt(input);
            if (!menus().containsKey(answer)) {
                System.out.println("Selected item does not exist!");
                return readOption();
            }
            return new AbstractMap.SimpleEntry<>(answer, menus().get(answer));
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number!");
            return readOption();
        }
    }

    @Override
    public State runState() {
        Map.Entry<Integer, MenuItem> selectedMenu = readOption();
        return nextState(selectedMenu);
    }

    protected void showMenu() {
        System.out.println("You have options: ");
        for (Map.Entry<Integer, MenuItem> entry : menus().entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().getText());
        }
    }

    public abstract void addMenuItem(Integer id, MenuItem menuItem, Supplier<State> state);
}
