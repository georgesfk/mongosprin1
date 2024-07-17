package com.example.mongosrping1.controller;

import com.example.mongosrping1.exception.UserNotFoundException;
import com.example.mongosrping1.model.Acteur;
import com.example.mongosrping1.model.User;
import com.example.mongosrping1.repository.ActeurRepository;
import com.example.mongosrping1.repository.AggregImpl;
import com.example.mongosrping1.repository.UserRepository;
import jakarta.servlet.ServletOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    @Qualifier("userRepository")
    @Autowired
    UserRepository userRepository;
    @Qualifier("acteurRepository")
    @Autowired
    private ActeurRepository acteurRepository;


    @Autowired
    AggregImpl aggregImpl;

    @GetMapping("/moyenage")
    public Double getMoyenneAge() {
        return aggregImpl.moyenne();
    }

    @GetMapping("/users")
    public List<User> list() {
        return userRepository.findAll();
    }

    @GetMapping("/acteurs")
    public List<Acteur> lista() {
        return acteurRepository.findAll();
    }

    @GetMapping("/moinsde/{age}")
    public List<User> list(@PathVariable("age") int age) {
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
    public User createProduct(@RequestBody User user) {
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

/*    @GetMapping("/listeacteurs")
    public String listeacteurs(Model model) {
        List<Acteur> acteurs = acteurRepository.findAll();
        model.addAttribute("acteurs", acteurs);
        return "listeacteurs";

    }*/

    @GetMapping("/listeacteurs")
    public String listActeur(Model model) {
        List<Acteur> acteurs = acteurRepository.findAll();
        model.addAttribute("acteurs", acteurs);
        System.out.println(acteurs);
        return "listacteurs";
    }
    @GetMapping("/test")
    public String test(Model model) {
        model.addAttribute("message","coucou");
        return "test";
    }
    }


