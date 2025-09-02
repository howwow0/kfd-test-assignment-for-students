package com.howwow.ui.book;

import com.howwow.service.BookService;
import com.howwow.ui.State;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BookSearchByTitleState implements State {
    private final BookService bookService;
    private final State backState;

    @Override
    public State runState() {
        bookService.findByTitle();
        return backState;
    }
}
