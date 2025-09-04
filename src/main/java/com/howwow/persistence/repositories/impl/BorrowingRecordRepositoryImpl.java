package com.howwow.persistence.repositories.impl;

import com.howwow.exception.BorrowingRecordCreationException;
import com.howwow.exception.BorrowingRecordNotFoundException;
import com.howwow.persistence.entities.BorrowingRecord;
import com.howwow.persistence.repositories.BorrowingRecordRepository;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class BorrowingRecordRepositoryImpl implements BorrowingRecordRepository {
    private final List<BorrowingRecord> borrowingRecords;

    @Override
    public List<BorrowingRecord> findOverdue() {
        Instant now = Instant.now();
        return borrowingRecords.stream()
                .filter(record -> record.getDueTime() != null &&
                        record.getDueTime().isBefore(now))
                .collect(Collectors.toList());
    }

    @Override
    public void create(BorrowingRecord borrowingRecord) {
        if (borrowingRecords.stream().anyMatch(br ->
                br.getIsbn().equals(borrowingRecord.getIsbn()) &&
                        br.getUserId().equals(borrowingRecord.getUserId()))) {
            throw new BorrowingRecordCreationException("Borrowing Record with ISBN " +
                    borrowingRecord.getIsbn() + " and User id " +
                    borrowingRecord.getUserId() + " already exists and is not returned yet");
        }
        borrowingRecords.add(borrowingRecord);
    }

    @Override
    public void removeByUserIdAndBookId(String userId, String bookId) {
        boolean isDeleted = borrowingRecords.removeIf(record ->
                record.getUserId().equals(userId) &&
                        record.getIsbn().equals(bookId));
        if (!isDeleted) {
            throw new BorrowingRecordNotFoundException("Borrowing record not found for user " +
                    userId + " and book " + bookId);
        }
    }

    @Override
    public long getBorrowingUserBookCount(String userId) {
        return borrowingRecords.stream().filter(record -> record.getUserId().equals(userId)).count();
    }

    @Override
    public boolean isBookBorrowed(String isbn) {
        return borrowingRecords.stream()
                .anyMatch(record -> record.getIsbn().equals(isbn));
    }
}
