package com.project.myproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")  // Base URL path for all methods
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // POST: Save a new user
    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user) {
        userRepository.save(user);
        return ResponseEntity.ok("User created successfully");
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // GET: Read a user by ID
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new RuntimeException("User not found with id " + id);
        }
    }


    // PUT: Update an existing user
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            existingUser.setName(userDetails.getName());
            existingUser.setEmail(userDetails.getEmail());
            return userRepository.save(existingUser);
        } else {
            throw new RuntimeException("User not found with id " + id);
        }
    }

    // DELETE: Delete a user by ID
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.deleteById(id);
            return "User with id " + id + " has been deleted";
        } else {
            throw new RuntimeException("User not found with id " + id);
        }
    }
}