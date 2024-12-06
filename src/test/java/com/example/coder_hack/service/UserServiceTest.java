package com.example.coder_hack.service;

import com.example.coder_hack.data.Badge;
import com.example.coder_hack.data.User;
import com.example.coder_hack.exchange.UpdateUserRequest;
import com.example.coder_hack.exchange.UserResponse;
import com.example.coder_hack.repository.UserRepository;
import com.example.coder_hack.service.implementation.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private UserServiceImpl userService;

  private ModelMapper modelMapper;

  @BeforeEach
  void setup() {
    modelMapper = new ModelMapper();
    userService = new UserServiceImpl(userRepository, modelMapper);
  }

  @Test
  void findAll() {
    User user1 = new User();
    user1.setUserId("1");
    user1.setScore(30);

    User user2 = new User();
    user2.setUserId("2");
    user2.setScore(60);

    User user3 = new User();
    user3.setUserId("3");
    user3.setScore(42);

    given(userRepository.findAll()).willReturn(List.of(user1, user2, user3));

    List<UserResponse> responses = userService.findAll();

    assertThat(responses).hasSize(3);
    assertThat(responses.getFirst().getScore()).isEqualTo(60);
    assertThat(responses.getLast().getScore()).isEqualTo(30);
    verify(userRepository, times(1)).findAll();

  }

  @Test
  void update() {
    User user = new User();
    user.setUserId("1");
    user.setBadges(new HashSet<>());

    given(userRepository.findById("1")).willReturn(Optional.of(user));
    given(userRepository.save(any(User.class))).willReturn(user);

    UpdateUserRequest request = new UpdateUserRequest();
    request.setScore(32);

    UserResponse response = userService.update("1", request);
    assertThat(response).isNotNull();
    assertThat(response.getBadges()).contains(Badge.CODE_CHAMP);

    request.setScore(20);
    response = userService.update("1", request);

    assertThat(response).isNotNull();
    assertThat(response.getBadges()).contains(Badge.CODE_NINJA);
    assertThat(response.getBadges()).contains(Badge.CODE_CHAMP);

    request.setScore(58);
    response = userService.update("1", request);

    assertThat(response).isNotNull();
    assertThat(response.getBadges()).contains(Badge.CODE_NINJA);
    assertThat(response.getBadges()).contains(Badge.CODE_CHAMP);

    request.setScore(72);
    response = userService.update("1", request);

    assertThat(response).isNotNull();
    assertThat(response.getBadges()).contains(Badge.CODE_NINJA);
    assertThat(response.getBadges()).contains(Badge.CODE_CHAMP);
    assertThat(response.getBadges()).contains(Badge.CODE_MASTER);

    assertThat(response.getBadges()).hasSizeLessThanOrEqualTo(3);
    verify(userRepository, times(4)).findById("1");
    verify(userRepository, times(4)).save(user);

  }
}