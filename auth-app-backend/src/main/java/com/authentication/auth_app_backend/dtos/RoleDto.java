package com.authentication.auth_app_backend.dtos;

import java.util.UUID;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {
  private UUID id;
  private String name;
}
