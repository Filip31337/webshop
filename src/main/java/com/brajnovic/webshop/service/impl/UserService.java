package com.brajnovic.webshop.service.impl;

import com.brajnovic.webshop.model.User;

public interface UserService {

    User getUserById(Long personId);

    User getUserByName(String userName);
}
