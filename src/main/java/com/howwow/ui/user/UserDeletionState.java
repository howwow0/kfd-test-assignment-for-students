package com.howwow.ui.user;

import com.howwow.service.UserService;
import com.howwow.ui.State;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserDeletionState implements State {
    private final UserService userService;
    private final State backState;

    @Override
    public State runState() {
        userService.removeUser();
        return backState;
    }
}
