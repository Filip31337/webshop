package com.brajnovic.webshop.controller;

import com.brajnovic.webshop.command.GetUserCommand;
import com.brajnovic.webshop.model.User;
import com.brajnovic.webshop.service.UserService;
import com.brajnovic.webshop.util.UrlConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping(UrlConstants.APP_CONTEXT_ROOT)
public class UserController {

    private final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(UrlConstants.GET_USER_BY_ID +"/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        if (userId == null) {
            return ResponseEntity.internalServerError().build();
        }
        User user;

        try {
            user = userService.getUserById(userId);
            log.info("User found using userId: " +  userId);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.noContent().header("errorMessage", e.getMessage()).build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().header("errorMessage", e.getMessage()).build();
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(UrlConstants.GET_USER_BY_USER_NAME + "/{userName}")
    public ResponseEntity<User> getUserByName(@PathVariable String userName) {
        if (userName == null) {
            return ResponseEntity.internalServerError().build();
        }
        User user;

        try {
            user = userService.getUserByName(userName);
            log.info("User found using userName: " +  userName);

        } catch (EntityNotFoundException e) {
            return ResponseEntity.noContent().header("errorMessage", e.getMessage()).build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().header("errorMessage", e.getMessage()).build();
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping(UrlConstants.GET_USER)
    public ResponseEntity<User> getUser(@RequestBody GetUserCommand getUserCommand) {
        if (getUserCommand == null) {
            return ResponseEntity.internalServerError().build();
        }
        User user;

        try {
            user = userService.getUser(getUserCommand);
            log.info("User found using getUserCommand: " + getUserCommand);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.noContent().header("errorMessage", e.getMessage()).build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().header("errorMessage", e.getMessage()).build();
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(UrlConstants.GET_USERS)
    public ResponseEntity<List<User>> getUsers() {
        List<User> userList;

        try {
            userList = userService.getUsers();
            log.info("Users found: " + userList.size() + ".");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.noContent().header("errorMessage", e.getMessage()).build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().header("errorMessage", e.getMessage()).build();
        }

        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

}
