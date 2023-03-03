package com.example.CoronaApi.controller;

import com.example.CoronaApi.Cassandra.dataClasses.UserCass;
import com.example.CoronaApi.Cassandra.repositories.UserRepositoryCass;
import com.example.CoronaApi.model.response.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserRepositoryCass userRepository;

    @GetMapping("/")
    public String  getUsers(){
        Iterable<UserCass> userCasses = userRepository.findAll();
        AtomicReference<String> tulleString = new AtomicReference<>("");
        userCasses.forEach(e -> tulleString.set(tulleString + e.getUsername()));
        String tulleString2 = tulleString.toString();
         return "ser du dette e du dum " + tulleString2;
    }

    @GetMapping("/add")
    public void addUser(){
        UserCass user = new UserCass();
        user.setUserId(UUID.randomUUID());
        user.setName("Alice");
        user.setUsername("alice@example.com");
        user.setPassword("secret");
        user.setRole("USER");

// Save the user object
        userRepository.save(user);
    }
}