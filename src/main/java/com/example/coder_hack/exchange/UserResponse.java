package com.example.coder_hack.exchange;

import com.example.coder_hack.data.Badge;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class UserResponse {
  private String userId;
  private String username;
  private int score;
  private Set<Badge> badges;
}
