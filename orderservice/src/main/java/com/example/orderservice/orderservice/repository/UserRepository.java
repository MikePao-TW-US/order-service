package com.example.orderservice.orderservice.repository;

import com.example.orderservice.orderservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUserId(Integer userId);

    User findByEmail(String email);
}
