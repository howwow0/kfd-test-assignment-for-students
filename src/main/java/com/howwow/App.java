package com.howwow;

import com.howwow.ui.*;

public class App {
    public static void main(String[] args) {
        MenuCreator menuCreator = new MenuCreator();
        State runningState = menuCreator.createMenu();
        while (runningState != null) {
            runningState = runningState.runState();
        }
    }
}
