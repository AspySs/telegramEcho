package com.example.testtask.repository;

import com.example.testtask.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    @Transactional
    @Modifying
    @Query("update User u set u.message = ?1 where u.username = ?2")
    void updateMessageByUsername(@NonNull String message, String username);
    @Query("select u from User u where u.username = ?1")
    Optional<User> findByUsername(String username);
    @Transactional
    @Modifying
    @Query("update User u set u.counter = ?1 where u.username = ?2")
    void updateCounterByUsername(@NonNull Long counter, String username);
    @Query("select (count(u) > 0) from User u where u.username = ?1")
    boolean existsByUsername(String username);

}