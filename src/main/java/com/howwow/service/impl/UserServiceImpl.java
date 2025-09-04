package com.howwow.service.impl;

import com.howwow.persistence.entities.Faculty;
import com.howwow.persistence.entities.Guest;
import com.howwow.persistence.entities.Student;
import com.howwow.persistence.entities.User;
import com.howwow.persistence.repositories.UserRepository;
import com.howwow.service.UserService;
import com.howwow.ui.utils.ScannerUtils;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.Scanner;

@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final Scanner scanner;

    @Override
    public void registerUser() {
        System.out.println("=== Register New User ===");
        User newUser;
        switch (ScannerUtils.getValidatedInput("Enter User Type (Guest, Student, Faculty): ", "User Type", scanner)) {
            case "Guest" -> newUser = new Guest();
            case "Student" -> newUser = new Student();
            case "Faculty" -> newUser = new Faculty();
            default -> {
                System.out.println("You have entered an invalid user type. Try again.");
                return;
            }
        }
        newUser.setUserId(ScannerUtils.getValidatedInput("Enter User ID: ", "User ID", scanner));
        newUser.setName(ScannerUtils.getValidatedInput("Enter User Name: ", "User Name", scanner));
        newUser.setEmail(ScannerUtils.getValidatedInput("Enter User Email: ", "User Email", scanner));
        userRepository.create(newUser);
        System.out.println("User successfully registered!");
    }

    @Override
    public void removeUser() {
        System.out.println("=== Remove User ===");

        String userId = ScannerUtils.getValidatedInput("Enter User ID: ", "User ID", scanner);
        Optional<User> userToRemove = userRepository.findByUserId(userId);
        if (userToRemove.isEmpty()) {
            System.out.println("User with id " + userId + " not found!");
            return;
        }

        User user = userToRemove.get();
        System.out.println("Found user: " + user.getName());

        String confirmation = ScannerUtils.getValidatedInput("Are you sure you want to delete this user? (yes/no): ", "Confirmation", scanner);

        if (confirmation.equalsIgnoreCase("yes") || confirmation.equalsIgnoreCase("y")) {
            userRepository.removeByUserId(userId);
            System.out.println("User successfully removed!");
        } else {
            System.out.println("User removal cancelled.");
        }
    }
}
