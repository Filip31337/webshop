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

    @PostMapping(UrlConstants.GET_USER_BY_ID)
    public ResponseEntity<User> getUserById(@RequestBody GetUserCommand command) {
        User response = userService.getUserById(command.getPersonId());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(UrlConstants.GET_USER_BY_USER_NAME)
    public ResponseEntity<User> getUserByName(@RequestBody GetUserCommand command) {
        User response = userService.getUserByName(command.getPersonName());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
