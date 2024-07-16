package com.example.mongosrping1.controller;

import com.example.mongosrping1.exception.UserNotFoundException;
import com.example.mongosrping1.model.User;
import com.example.mongosrping1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

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
    public User getUserById(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new UserNotFoundException("User not found with id: " + id);
        }
    }

    @PostMapping("/create")
    public User createProduct (@RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping("/update/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User updatedUser) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            existingUser.setFirstName(updatedUser.getFirstName());
            existingUser.setLastName(updatedUser.getLastName());
            existingUser.setAge(updatedUser.getAge()); // Mettez à jour d'autres champs si nécessaire
            return userRepository.save(existingUser);
        } else {
            throw new UserNotFoundException("User not found with id: " + id);
        }
    }
}

