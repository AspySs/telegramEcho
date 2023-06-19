package com.example.testtask.controller;

import com.example.testtask.entity.User;
import com.example.testtask.exception.UserNotFoundException;
import com.example.testtask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/message")
public class MessageController {
    @Autowired
    UserService service;
    @Autowired
    public  MessageController(UserService userService){this.service = userService;}

    @GetMapping("/last/{userId}")
    public ResponseEntity<String> getLastMessage(@PathVariable String userId){
        try{
            User usr = service.findById(userId);
            return new ResponseEntity<>("{\n" +
                    "“username”: "+usr.getUsername()+"\n" +
                    "\"text\" : "+usr.getMessage()+"\n" +
                    "}", HttpStatusCode.valueOf(200));
        }catch (UserNotFoundException e){
            return new ResponseEntity<>( e.getMessage(), HttpStatusCode.valueOf(503));
        }
    }

}
