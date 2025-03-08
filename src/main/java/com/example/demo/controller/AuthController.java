package com.example.demo.controller;

import com.example.demo.config.component.JwtUtil;
import com.example.demo.model.entity.EntityUser;
import com.example.demo.model.enums.Role;
import com.example.demo.repository.EntityUserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Tag(name = "аутентификация", description = "Управление верефикацией и jwt-token")
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final EntityUserRepository userRepository;

    @PostMapping("/login")
    @Operation(summary = "Вход и получение пользователем токена входа", description = "Войти и поулчить токен входа")
    @ApiResponse(responseCode = "200", description = "Вошли, токен получен")
    public String login(@RequestParam String email, @RequestParam String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        return jwtUtil.generateToken(email);
    }

    @PostMapping("/register")
    @Operation(summary = "Регистрация пользователя")
    @ApiResponse(responseCode = "200", description = "Пользователь зарегистрирован")
    public String register(@RequestParam String email, @RequestParam String password, @RequestParam(defaultValue = "USER") String role) {
        if (userRepository.findByEmail(email).isPresent()) {
            return "Пользователь уже существует!";
        }

        Role userRole = (role == null || role.isEmpty()) ? Role.USER : Role.valueOf(role.toUpperCase());

        EntityUser user = new EntityUser();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(userRole);

        userRepository.save(user);
        return "Пользователь зарегистрирован!";
    }
}

