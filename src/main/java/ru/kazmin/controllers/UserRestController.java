package ru.kazmin.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kazmin.models.Bid;
import ru.kazmin.service.abstracts.UserService;

@RestController
@RequestMapping("/user")
public class UserRestController {
    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<HttpStatus> createBid(@RequestBody Bid bid) {
        //userService.createBid(bid);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
