package com.example.testtask.service;

import com.example.testtask.entity.User;
import com.example.testtask.exception.UserNotFoundException;
import com.example.testtask.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {this.repository = repository;}

    public User findByName(String name){
        if(!name.isEmpty()){
            Optional<User> user = repository.findByUsername(name);
            if(user.isPresent()){
                return user.get();
            }else{
                throw new UserNotFoundException("User with this name not found");
            }
        }
        throw new IllegalArgumentException("Username is empty");
    }

    public boolean userIsExist(String username){
        if(username.isEmpty()){throw new IllegalArgumentException("Username is empty");}
        return repository.existsByUsername(username);
    }


    @Transactional
    @Modifying
    public void updateCounter(Long counter, String username){
        if(userIsExist(username)){
            User usr = findByName(username);
            repository.updateCounterByUsername(usr.getCounter()+1,username);
        }else{
            throw new UserNotFoundException("User with this name not found");
        }
    }

    public User findById(String id){
        Optional<User> usr = repository.findById(id);
        if(usr.isEmpty()){throw new UserNotFoundException("User with this id not found");}
        return usr.get();
    }


    @Transactional
    @Modifying
    public void add(User user){
        if(!userIsExist(user.getUsername())){
            repository.save(user);
        }
    }


    @Transactional
    @Modifying
    public void updateMessageByUsername(@NonNull String message, String username){
        if(userIsExist(username)){
            repository.updateMessageByUsername(message, username);
        }else{
            throw new UserNotFoundException("User with this name not found");
        }
    }

}
