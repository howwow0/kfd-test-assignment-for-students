package com.howwow.persistence.repositories;

import com.howwow.persistence.entities.User;

import java.util.Optional;

public interface UserRepository {
    void create(User user);

    Optional<User> findByUserId(String userId);

    void removeByUserId(String userId);
}
