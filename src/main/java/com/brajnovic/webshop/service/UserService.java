package com.brajnovic.webshop.service;

import com.brajnovic.webshop.command.GetUserCommand;
import com.brajnovic.webshop.model.User;

import java.util.List;

public interface UserService {

    User getUserById(Long personId);

    User getUserByName(String userName);

    User getUser(GetUserCommand getUserCommand);

    List<User> getUsers();
}
