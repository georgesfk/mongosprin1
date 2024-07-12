package com.example.mongosrping1.controller;

import com.example.mongosrping1.model.User;
import com.example.mongosrping1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Qualifier("userRepository")
    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    public List<User> list() {
        return userRepository.findAll();}

    @GetMapping("/moinsde/{age}")
    public List<User> list(@PathVariable("age")int age) {
        return userRepository.findPersonOlderThan(age);
    }

    @GetMapping("/user/{id}")
    public Optional<User> getOne(@PathVariable("id") int id) {
        return userRepository.findById(id);
    }

}
