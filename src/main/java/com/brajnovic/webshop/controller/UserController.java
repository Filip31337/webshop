package com.brajnovic.webshop.controller;

import com.brajnovic.webshop.command.GetUserCommand;
import com.brajnovic.webshop.model.User;
import com.brajnovic.webshop.service.impl.UserService;
import com.brajnovic.webshop.util.UrlConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(UrlConstants.APP_CONTEXT_ROOT)
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(UrlConstants.GET_USER_BY_ID +"/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        if (userId == null) {
            return ResponseEntity.notFound().build();
        }
        User response = userService.getUserById(userId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(UrlConstants.GET_USER_BY_USER_NAME + "/{userName}")
    public ResponseEntity<User> getUserByName(@PathVariable String userName) {
        if (userName == null) {
            return ResponseEntity.notFound().build();
        }
        User response = userService.getUserByName(userName);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(UrlConstants.GET_USER)
    public ResponseEntity<User> getUser(@RequestBody GetUserCommand getUserCommand) {
        if (getUserCommand == null) {
            return ResponseEntity.notFound().build();
        }
        User response = userService.getUser(getUserCommand);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
