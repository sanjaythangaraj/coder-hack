package com.example.coder_hack.exchange;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class CreateUserRequest {
  @NotBlank(message = "Username is mandatory")
  private String username;
}
