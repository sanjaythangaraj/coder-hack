package com.example.coder_hack.repository;

import com.example.coder_hack.data.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

  Optional<User> findByUsername(String username);
}
