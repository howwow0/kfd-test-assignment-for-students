package com.howwow.ui.borrowingrecord;

import com.howwow.exception.BorrowingRecordCreationException;
import com.howwow.service.BorrowingRecordService;
import com.howwow.ui.State;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BorrowingRecordBorrowState implements State {

    private final BorrowingRecordService borrowingRecordService;
    private final State backState;

    @Override
    public State runState() {
        try {
            borrowingRecordService.borrowBook();
            return backState;
        } catch (BorrowingRecordCreationException e) {
            System.out.println(e.getMessage());
            return backState;
        }
    }
}
