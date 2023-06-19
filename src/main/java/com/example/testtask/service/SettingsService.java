package com.example.testtask.service;


import com.example.testtask.entity.Settings;
import com.example.testtask.exception.SettingsNotFoundException;
import com.example.testtask.repository.SettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class SettingsService {

    @Autowired
    private final SettingsRepository repository;

    @Autowired
    public SettingsService(SettingsRepository settingsRepository) {
        this.repository = settingsRepository;
    }


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

    boolean isExists(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Settings name is empty!");
        }
        return repository.existsByName(name);
    }

    @Transactional
    @Modifying
    public void add(Settings settings) {
        if (!isExists(settings.getName())) {
            repository.save(settings);
        }
    }

    @Transactional
    @Modifying
    public void updateDelayByName(Long delay, String name) {
        if (isExists(name)) {
            Settings settings = findSettingsByName(name);
            settings.setDelay(delay);
            repository.updateDelayByName(settings.getDelay(), name);
        }
    }
}
