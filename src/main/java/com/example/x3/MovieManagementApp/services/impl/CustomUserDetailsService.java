package com.example.x3.MovieManagementApp.services.impl;

import com.example.x3.MovieManagementApp.entities.User;
import com.example.x3.MovieManagementApp.repositories.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String displayNameOrEmail) throws UsernameNotFoundException {
        User user = userRepository.findByDisplayNameOrEmail(displayNameOrEmail, displayNameOrEmail)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with display name or email:" + displayNameOrEmail));
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_USER")));
    }


}
