package com.example.coder_hack.controller;

import com.example.coder_hack.exchange.CreateUserRequest;
import com.example.coder_hack.exchange.UpdateUserRequest;
import com.example.coder_hack.exchange.UserResponse;
import com.example.coder_hack.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(value = "/users")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping()
  public ResponseEntity<List<UserResponse>> fetchAllUsers() {
    List<UserResponse> responses = userService.findAll();
    return ResponseEntity.status(HttpStatus.OK).body(responses);
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserResponse> fetchUser(@PathVariable String id) {
    UserResponse response = userService.findById(id);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @PostMapping()
  public ResponseEntity<UserResponse> createUser(@RequestBody @Valid CreateUserRequest createUserRequest) {
    UserResponse response = userService.create(createUserRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @PutMapping("/{id}")
  public ResponseEntity<UserResponse> updateUser(
      @PathVariable String id,
      @RequestBody @Valid UpdateUserRequest updateUserRequest) {
    UserResponse response = userService.update(id, updateUserRequest);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<UserResponse> deleteUser(@PathVariable String id) {
    userService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
