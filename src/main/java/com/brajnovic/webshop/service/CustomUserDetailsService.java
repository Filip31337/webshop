package com.brajnovic.webshop.service;

import com.brajnovic.webshop.entity.UserEntity;
import com.brajnovic.webshop.model.CustomUserDetails;
import com.brajnovic.webshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                Supplier<UsernameNotFoundException> exceptionSupplier = () -> new UsernameNotFoundException("Username not found in databse!");

        UserEntity userEntity = userRepository.findUserByUsername(username).orElseThrow(exceptionSupplier);

        return new CustomUserDetails(UserEntity.convertToUser(userEntity));
    }

}
