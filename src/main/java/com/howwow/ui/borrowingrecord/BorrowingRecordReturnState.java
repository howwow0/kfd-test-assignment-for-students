package com.howwow.ui.borrowingrecord;

import com.howwow.exception.BorrowingRecordNotFoundException;
import com.howwow.service.BorrowingRecordService;
import com.howwow.ui.State;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BorrowingRecordReturnState implements State {

    private final BorrowingRecordService borrowingRecordService;
    private final State backState;

    @Override
    public State runState() {
        try {
            borrowingRecordService.returnBook();
            return backState;
        } catch (BorrowingRecordNotFoundException e) {
            System.out.println(e.getMessage());
            return backState;
        }
    }
}
