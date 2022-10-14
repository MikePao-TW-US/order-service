package com.multicart.api.repository;

import com.multicart.api.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Integer> {

    User findByUserId(Integer userId);

    User findByEmail(String userName);
}
