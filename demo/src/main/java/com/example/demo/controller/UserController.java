package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/api/users")
@Tag(name = "UserController", description = "Perform CRUD operations on users")
public class UserController {
    
    @Autowired
    UserService userservice;
    
    @GetMapping
    public List<User> getAllUser() {
        return userservice.getUser();
    }

    
    @PostMapping("/add")
    public Object addUser(@RequestBody User user) {
        return userservice.addUser(user);
    }
    
    @GetMapping("/{id}")
    public Object getUserById(@PathVariable Long id) {
        return userservice.getUserById(id);
    }
    
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        return userservice.deleteUser(id);
    }
    
    
    @PutMapping("/{id}")
    @Operation(summary = "Update a user by ID", description = "Provide an ID to update an existing user")
    public Object updateUser(@PathVariable Long id, @RequestBody User user) {
        return userservice.updateUser(id, user);
    }
    
    
    @GetMapping("paginate")
    public Page<User> getUserPage(@RequestParam int page, @RequestParam int size)
    {
        return userservice.getUserPage(page, size);
    }
    
    
    @GetMapping("sorting")
    public List<User> sortbyname(@RequestParam String field)
    {
        return userservice.sortbyname(field);
    }

    @GetMapping("count-by-domain")
    public Long countUsersByEmailDomain(@RequestParam String domain) {
        return userservice.countUsersByEmailDomain(domain);
    }
}
