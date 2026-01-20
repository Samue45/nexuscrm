package com.nexuscrm.app.controller;

import com.nexuscrm.app.model.User;
import com.nexuscrm.app.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<User> listUsers() {
        return service.findAll();
    }

    @PostMapping
    public User createNewUser(@RequestBody User newUser) {
        return service.save(newUser);
    }

    @GetMapping("/buscar/{name}")
    public User searchUser(@PathVariable String name) {
        return service.findUserByName(name);
    }

    @GetMapping("/buscar")
    public User searchUserByParameter(@RequestParam String name) {
        return service.findUserByName(name);
    }
}

