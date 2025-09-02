package com.howwow.ui.book;

import com.howwow.exception.BookCreationException;
import com.howwow.service.BookService;
import com.howwow.ui.State;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BookCreationState implements State {
    private final BookService bookService;
    private final State backState;

    @Override
    public State runState() {
        try {
            bookService.createBook();
            return backState;
        } catch (BookCreationException e) {
            System.out.println("ISBN already exists. Please try again with a different ISBN.");
            return this;
        }
    }
}
