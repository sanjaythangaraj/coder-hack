package com.example.coder_hack.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document(collection="user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
  @Id
  private String userId;
  private String username;
  private int score;
  private Set<Badge> badges;
}
