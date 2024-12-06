package com.example.coder_hack.exception;

public class UserAlreadyExistsException extends RuntimeException {
  public UserAlreadyExistsException() {
    super("User with the username already exists!");
  }

  public UserAlreadyExistsException(String message) {
    super(message);
  }
}
