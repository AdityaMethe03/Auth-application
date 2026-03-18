package com.authentication.auth_app_backend.services.impl;

import com.authentication.auth_app_backend.dtos.UserDto;
import com.authentication.auth_app_backend.services.AuthService;
import com.authentication.auth_app_backend.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final UserService userService;

  @Override
  public UserDto registerUser(UserDto userDto) {

    // logic
    // verifying email or password etc.
    // default role
    UserDto userDto1 = userService.createUser(userDto);
    return userDto1;
  }
}
