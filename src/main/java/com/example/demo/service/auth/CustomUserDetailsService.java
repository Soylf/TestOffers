package com.example.demo.service.auth;

import com.example.demo.model.entity.EntityUser;
import com.example.demo.repository.EntityUserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final EntityUserRepository userRepository;

    public CustomUserDetailsService(EntityUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        EntityUser user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Что-то не так"));

        return User.withUsername(user.getEmail())
                .password(user.getPassword())
                .roles(String.valueOf(user.getRole()))
                .build();
    }
}
