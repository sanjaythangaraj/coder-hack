package com.example.coder_hack.service;

import com.example.coder_hack.exchange.CreateUserRequest;
import com.example.coder_hack.exchange.UpdateUserRequest;
import com.example.coder_hack.exchange.UserResponse;

import java.util.List;

public interface UserService {
  public UserResponse create(CreateUserRequest createUserRequest);
  public UserResponse findById(String id);
  public List<UserResponse> findAll();
  public UserResponse update(String id, UpdateUserRequest updateUserRequest);
  public void deleteById(String id);
}
