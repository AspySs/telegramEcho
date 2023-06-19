package com.example.testtask.repository;

import com.example.testtask.entity.Settings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface SettingsRepository extends JpaRepository<Settings, String> {
    @Query("select (count(s) > 0) from Settings s where s.name = ?1")
    boolean existsByName(String name);

    @Query("select s from Settings s where s.name = ?1")
    Optional<Settings> findByName(String name);

    @Transactional
    @Modifying
    @Query("update Settings s set s.delay = ?1 where s.name = ?2")
    void updateDelayByName(Long delay, String name);
}