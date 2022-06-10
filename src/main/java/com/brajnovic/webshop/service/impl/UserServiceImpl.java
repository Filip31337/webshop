package com.brajnovic.webshop.service.impl;

import com.brajnovic.webshop.command.GetUserCommand;
import com.brajnovic.webshop.entity.UserEntity;
import com.brajnovic.webshop.model.User;
import com.brajnovic.webshop.repository.UserRepository;
import com.brajnovic.webshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(Long personId) throws EntityNotFoundException, IllegalArgumentException {
        if (personId == null) {
            throw new IllegalArgumentException("Prazan je personId argument");
        }

        UserEntity userEntity = userRepository.findById(personId).orElseThrow(() -> new EntityNotFoundException("No user found by id: " + personId));

        return UserEntity.convertToUser(userEntity);
    }

    @Override
    public User getUserByName(String userName) throws EntityNotFoundException, IllegalArgumentException {
        if (userName == null || userName.isEmpty()) {
            throw new IllegalArgumentException("Prazan je userName argument");
        }
        UserEntity userEntity;

        userEntity = userRepository.findByUsername(userName).orElseThrow(() -> new EntityNotFoundException("No user found by name: " + userName));


        return UserEntity.convertToUser(userEntity);
    }

    @Override
    public User getUser(GetUserCommand getUserCommand) throws EntityNotFoundException, IllegalArgumentException {
        if (getUserCommand == null) {
            throw new IllegalArgumentException("Nedostaje command getUserCommand.");
        }
        UserEntity userEntity;

        userEntity = userRepository.findById(getUserCommand.getPersonId())
                .orElseGet(() -> userRepository.findByUsername(getUserCommand.getPersonName())
                .orElseThrow(() -> new EntityNotFoundException("No user found by name: " + getUserCommand.getPersonName() + " or id: " + getUserCommand.getPersonId().toString()))
        );

        return UserEntity.convertToUser(userEntity);
    }

    @Override
    public List<User> getUsers() throws EntityNotFoundException {
        List<User> userList = new ArrayList<>();

        List<UserEntity> userEntityList = new ArrayList<>(userRepository.findAll());

        userEntityList.forEach(userEntity -> userList.add(UserEntity.convertToUser(userEntity)));

        return userList;
    }

}
