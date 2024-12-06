package com.example.coder_hack.exception;

public class UserNotFoundException extends RuntimeException {
  public UserNotFoundException() {
    super("User with the given id not found!");
  }

  public UserNotFoundException(String message) {
    super(message);
  }
}
