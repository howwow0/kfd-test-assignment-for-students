package com.howwow.ui.borrowingrecord;

import com.howwow.service.BorrowingRecordService;
import com.howwow.ui.State;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BorrowingRecordOverdueState implements State {

    private final BorrowingRecordService borrowingRecordService;
    private final State backState;

    @Override
    public State runState() {
        borrowingRecordService.getOverdueBooks();
        return backState;
    }
}
