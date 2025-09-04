package com.howwow.ui;


import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Конфигурируемое меню с возможностью добавления пунктов и переходов между состояниями
 * Каждый пункт меню связан с целевым состоянием, в которое переходит приложение при выборе
 */
public class ConfigurableMenuState extends MenuState {
    private final Map<Integer, MenuItem> menuItems = new HashMap<>();
    private final Map<Integer, Supplier<State>> transitions = new HashMap<>();

    @Override
    protected Map<Integer, MenuItem> menus() {
        return menuItems;
    }

    @Override
    protected State nextState(Map.Entry<Integer, MenuItem> selectedMenu) {
        return transitions.get(selectedMenu.getKey()).get();
    }

    @Override
    public void addMenuItem(Integer id, MenuItem menuItem, Supplier<State> state) {
        menuItems.put(id, menuItem);
        transitions.put(id, state);
    }
}
