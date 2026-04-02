package com.authentication.auth_app_backend.controllers;

import com.authentication.auth_app_backend.dtos.UserDto;
import com.authentication.auth_app_backend.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
@AllArgsConstructor
public class UserController {

  private final UserService userService;

  // create user api
  @PostMapping
  public ResponseEntity<UserDto> createUser(@RequestBody UserDto user) {
    return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));
  }

  // get all user api
  @GetMapping
  public ResponseEntity<Iterable<UserDto>> getAllUsers() {
    return ResponseEntity.ok(userService.getAllUsers());
  }

  // get user by email
  @GetMapping("/email/{email}")
  public ResponseEntity<UserDto> getUserByEmailId(@PathVariable String email) {
    return ResponseEntity.ok(userService.getUserByEmail(email));
  }

  // delete user
  // api/v1/users/{userId}
  @DeleteMapping("/{userId}")
  public void deleteUser(@PathVariable("userId") String userId) {
    userService.deleteUser(userId);
  }

  // update user
  // api/v1/users/{userId}
  @PutMapping("/{userId}")
  public ResponseEntity<UserDto> updateUser(
      @PathVariable("userId") String userId, @RequestBody UserDto userDto) {
    return ResponseEntity.ok(userService.updateUser(userDto, userId));
  }

  // get user by id
  // api/v1/users/{userId}
  @GetMapping("/{userId}")
  public ResponseEntity<UserDto> getUserById(@PathVariable("userId") String userId) {
    return ResponseEntity.ok(userService.getUserById(userId));
  }
}
