package ru.kazmin.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kazmin.models.User;
import ru.kazmin.service.abstracts.UserService;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminRestController {
    private final UserService userService;

    public AdminRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/users/{username}")
    public User getUser(@PathVariable("username") String username) {
        return userService.getUser(username);
    }
    @PatchMapping("/users/{username}")
    public ResponseEntity<HttpStatus> setOperatorRole(@PathVariable("username") String username) {
        userService.setOperatorRole(userService.getUser(username));
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
