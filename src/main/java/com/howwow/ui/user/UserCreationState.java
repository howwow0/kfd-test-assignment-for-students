package com.howwow.ui.user;

import com.howwow.exception.BookCreationException;
import com.howwow.service.UserService;
import com.howwow.ui.State;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserCreationState implements State {
    private final UserService userService;
    private final State backState;

    @Override
    public State runState() {
        try {
            userService.registerUser();
            return backState;
        } catch (BookCreationException e) {
            System.out.println(e.getMessage());
            return backState;
        }
    }
}
