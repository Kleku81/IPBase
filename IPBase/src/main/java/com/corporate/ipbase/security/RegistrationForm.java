package com.corporate.ipbase.security;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.corporate.ipbase.domain.User;

import lombok.Data;


@Data
public class RegistrationForm {

  private String username;
  private String password;

  
  public User toUser(PasswordEncoder passwordEncoder) {
    return new User(
        username, passwordEncoder.encode(password));
  }
}
