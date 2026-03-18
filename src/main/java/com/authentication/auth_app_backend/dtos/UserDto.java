package com.authentication.auth_app_backend.dtos;

import com.authentication.auth_app_backend.entities.Provider;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
  private UUID id;
  private String email;
  private String name;
  private String password;
  private String image;
  private boolean enable = true;
  private Instant createdAt = Instant.now();
  private Instant updatedAt = Instant.now();
  private Provider provider = Provider.LOCAL;
  private Set<RoleDto> roles = new HashSet<>();
}
