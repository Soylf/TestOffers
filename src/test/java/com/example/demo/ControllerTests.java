package com.example.demo;


import com.example.demo.config.component.JwtUtil;
import com.example.demo.controller.AuthController;
import com.example.demo.repository.EntityUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ControllerTests {
    private static final Logger log = LoggerFactory.getLogger(ControllerTests.class);
    @Mock
    private EntityUserRepository userRepository;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldLoginAndReturnNonNullToken() {
        String email = "test@example.com";
        String password = "password123";

        when(userRepository.findByEmail(email)).thenReturn(java.util.Optional.empty());
        when(userRepository.save(any())).thenReturn(null);

        when(authenticationManager.authenticate(any())).thenReturn(null);
        when(jwtUtil.generateToken(email)).thenReturn("mockToken123");

        String registerResult = authController.register(email, password, "USER");
        assertEquals("Пользователь зарегистрирован!", registerResult);

        String result = authController.login(email, password);
        assertNotNull(result, "Токен не должен быть null");
    }

}
