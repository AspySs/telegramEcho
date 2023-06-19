package com.example.testtask.controller;


import com.example.testtask.entity.Settings;
import com.example.testtask.exception.SettingsNotFoundException;
import com.example.testtask.service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/delay")
public class SettingsController {
    @Autowired
    SettingsService service;

    @Autowired
    public SettingsController(SettingsService settingsService) {
        this.service = settingsService;
    }

    @PostMapping(value = "/update", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> updateDelay(@RequestBody Settings delay) {
        try {
            service.updateDelayByName(delay.getDelay(), "delay");
            return new ResponseEntity<>(HttpStatusCode.valueOf(200));
        } catch (SettingsNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(503));
        }
    }
}
