package com.howwow.service.impl;

import com.howwow.persistence.entities.BorrowingRecord;
import com.howwow.persistence.entities.User;
import com.howwow.persistence.repositories.BorrowingRecordRepository;
import com.howwow.persistence.repositories.UserRepository;
import com.howwow.service.BorrowingRecordService;
import com.howwow.ui.utils.ScannerUtils;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@RequiredArgsConstructor
public class BorrowingRecordServiceImpl implements BorrowingRecordService {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
            .withZone(ZoneId.systemDefault());
    private final BorrowingRecordRepository borrowingRecordRepository;
    private final UserRepository userRepository;
    private final Scanner scanner;

    @Override
    public void borrowBook() {
        System.out.println("=== Borrow Book ===");

        BorrowingRecord borrowingRecord = new BorrowingRecord();
        borrowingRecord.setIsbn(ScannerUtils.getValidatedInput("Enter ISBN: ", "ISBN", scanner));
        borrowingRecord.setUserId(ScannerUtils.getValidatedInput("Enter User ID: ", "ID", scanner));
        Optional<User> userOpt = userRepository.findByUserId(borrowingRecord.getUserId());
        if (userOpt.isEmpty()) {
            System.out.println("User with user id " + borrowingRecord.getUserId() + " not found. Try again.");
            return;
        }

        if (borrowingRecordRepository.isBookBorrowed(borrowingRecord.getIsbn())) {
            System.out.println("Book with ISBN " + borrowingRecord.getIsbn() + " is already borrowed. Try again.");
            return;
        }
        User user = userOpt.get();

        long countBorrowingBooks = borrowingRecordRepository.getBorrowingUserBookCount(borrowingRecord.getUserId());
        if (countBorrowingBooks >= user.getMaxBooks()) {
            System.out.println("User with user id " + borrowingRecord.getUserId() + " is already borrowed max count books.");
            return;
        }
        int daysToDue = user.getBorrowDays();
        Instant dueTime = Instant.now().plus(daysToDue, ChronoUnit.DAYS);
        borrowingRecord.setDueTime(dueTime);
        borrowingRecordRepository.create(borrowingRecord);
        System.out.println("Book successfully borrowed!");
        System.out.println("Due date: " + FORMATTER.format(dueTime));
    }

    @Override
    public void returnBook() {
        System.out.println("=== Return Book ===");

        String isbn = ScannerUtils.getValidatedInput("Enter ISBN: ", "ISBN", scanner);
        String userId = ScannerUtils.getValidatedInput("Enter User ID: ", "ID", scanner);

        borrowingRecordRepository.removeByUserIdAndBookId(userId, isbn);
        System.out.println("Book successfully returned!");
    }

    @Override
    public void getOverdueBooks() {
        System.out.println("=== Overdue Books ===");

        List<BorrowingRecord> overdueRecords = borrowingRecordRepository.findOverdue();

        if (overdueRecords.isEmpty()) {
            System.out.println("No overdue books found.");
            return;
        }
        System.out.println("Overdue books (" + overdueRecords.size() + "):");
        System.out.println("--------------------------------------------------");
        System.out.printf("%-15s %-15s %-20s%n", "User ID", "ISBN", "Due Date");
        System.out.println("--------------------------------------------------");

        for (BorrowingRecord record : overdueRecords) {
            System.out.printf("%-15s %-15s %-20s%n",
                    record.getUserId(),
                    record.getIsbn(),
                    FORMATTER.format(record.getDueTime()));
        }
        System.out.println("--------------------------------------------------");
    }
}
