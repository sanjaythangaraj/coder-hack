package com.example.coder_hack.service.implementation;

import com.example.coder_hack.data.Badge;
import com.example.coder_hack.data.User;
import com.example.coder_hack.exception.UserAlreadyExistsException;
import com.example.coder_hack.exception.UserNotFoundException;
import com.example.coder_hack.exchange.CreateUserRequest;
import com.example.coder_hack.exchange.UpdateUserRequest;
import com.example.coder_hack.exchange.UserResponse;
import com.example.coder_hack.repository.UserRepository;
import com.example.coder_hack.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final ModelMapper modelMapper;

  public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
    this.userRepository = userRepository;
    this.modelMapper = modelMapper;
  }

  @Override
  public UserResponse create(CreateUserRequest createUserRequest) {
    if (userRepository.findByUsername(createUserRequest.getUsername()).isPresent()) {
      throw new UserAlreadyExistsException();
    }

    User user = modelMapper.map(createUserRequest, User.class);
    user.setScore(0);
    user.setBadges(new HashSet<>());
    User savedUser = userRepository.save(user);
    return modelMapper.map(savedUser, UserResponse.class);
  }

  @Override
  public UserResponse findById(String id) {
    return userRepository.findById(id)
        .map(user -> modelMapper.map(user, UserResponse.class))
        .orElseThrow(UserNotFoundException::new);
  }

  @Override
  public List<UserResponse> findAll() {
    return userRepository.findAll()
        .stream()
        .map(user -> modelMapper.map(user, UserResponse.class))
        .sorted((a, b) -> Integer.compare(b.getScore(), a.getScore()))
        .toList();
  }

  @Override
  public UserResponse update(String id, UpdateUserRequest updateUserRequest) {
    User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    int score = updateUserRequest.getScore();
    user.setScore(score);

    if (score < 30) {
      user.getBadges().add(Badge.CODE_NINJA);
    } else if (score < 60) {
      user.getBadges().add(Badge.CODE_CHAMP);
    } else {
      user.getBadges().add(Badge.CODE_MASTER);
    }

    User updatedUser = userRepository.save(user);
    return modelMapper.map(updatedUser, UserResponse.class);
  }

  @Override
  public void deleteById(String id) {
    findById(id);
    userRepository.deleteById(id);
  }
}
