package com.example.demo.model.dto;

import com.example.demo.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String email;
    private String password;
    private Role role;
}
