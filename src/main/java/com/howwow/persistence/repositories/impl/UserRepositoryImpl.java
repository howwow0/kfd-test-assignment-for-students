package com.howwow.persistence.repositories.impl;

import com.howwow.exception.BookCreationException;
import com.howwow.persistence.entities.Book;
import com.howwow.persistence.entities.User;
import com.howwow.persistence.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final Map<String, User> users;

    @Override
    public void create(User user) {
        if (users.containsKey(user.getUserId())) {
            throw new BookCreationException("User with id " + user.getUserId() + " already exists");
        }
        users.put(user.getUserId(), user);
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        return Optional.ofNullable(users.get(userId));
    }

    @Override
    public void removeByUserId(String userId) {
        users.remove(userId);
    }
}
