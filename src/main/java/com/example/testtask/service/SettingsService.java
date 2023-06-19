package com.example.testtask.service;


import com.example.testtask.entity.Settings;
import com.example.testtask.exception.SettingsNotFoundException;
import com.example.testtask.repository.SettingsRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SettingsService {
    @Autowired
    private final SettingsRepository repository;

    public Settings findSettingsByName(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Settings name is empty!");
        }

        Optional<Settings> sett = repository.findByName(name);
        if (sett.isEmpty()) {
            throw new SettingsNotFoundException("Settings not found by name");
        }

        return sett.get();
    }

    @Transactional
    @Modifying
    public void updateDelayByName(Long delay, String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Settings name is empty!");
        }

        boolean isNameExists = repository.existsByName(name);

        if (isNameExists) {
            Settings settings = findSettingsByName(name);
            settings.setDelay(delay);
            repository.updateDelayByName(settings.getDelay(), name);
        }
    }
}
