package com.example.testtask.service;

import com.example.testtask.entity.User;
import com.example.testtask.exception.UserNotFoundException;
import com.example.testtask.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    @Autowired
    private final UserRepository repository;

    public boolean userExists(String username) {
        if (username.isEmpty()) {
            throw new IllegalArgumentException("Username is empty");
        }
        return repository.existsByUsername(username);
    }

    public User findById(String id) {
        Optional<User> usr = repository.findById(id);
        if (usr.isEmpty()) {
            throw new UserNotFoundException("User with id " + id + " not found");
        }
        return usr.get();
    }

    @Transactional
    @Modifying
    public void add(User user) {
        if (!userExists(user.getUsername())) {
            repository.save(user);
        }
    }

    @Transactional
    @Modifying
    public void updateMessageByUsername(@NonNull String message, String username) {
        if (userExists(username)) {
            repository.updateMessageByUsername(message, username);
        } else {
            throw new UserNotFoundException("User with name " + username + " not found");
        }
    }

}
