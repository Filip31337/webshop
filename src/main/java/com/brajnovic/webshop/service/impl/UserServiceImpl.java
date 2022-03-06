package com.brajnovic.webshop.service.impl;

import com.brajnovic.webshop.entity.UserEntity;
import com.brajnovic.webshop.model.User;
import com.brajnovic.webshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(Long personId) {
        if (personId == null) {
            throw new IllegalArgumentException("Prazan je personId argument");
        }

        UserEntity userEntity = userRepository.findById(personId).orElse(null);

        return UserEntity.convertToUser(userEntity);
    }

    @Override
    public User getUserByName(String userName) {
        if (userName == null || userName.isEmpty()) {
            throw new IllegalArgumentException("Prazan je userName argument");
        }

        UserEntity userEntity = userRepository.findByUsername(userName).orElse(null);

        return UserEntity.convertToUser(userEntity);
    }

}