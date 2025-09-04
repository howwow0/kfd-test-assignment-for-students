package com.howwow.persistence.repositories;

import com.howwow.persistence.entities.BorrowingRecord;

import java.util.List;

public interface BorrowingRecordRepository {
    List<BorrowingRecord> findOverdue();

    void create(BorrowingRecord borrowingRecord);

    void removeByUserIdAndBookId(String userId, String bookId);

    long getBorrowingUserBookCount(String userId);

    boolean isBookBorrowed(String isbn);
}
