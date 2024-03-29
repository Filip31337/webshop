package com.brajnovic.webshop.repository;

import com.brajnovic.webshop.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findById(Integer personId);

    Optional<UserEntity> findByUsername(String name);

    Optional<UserEntity> findUserByUsername(String username);

}
