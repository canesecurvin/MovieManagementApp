package com.example.x3.MovieManagementApp.services.impl;

import com.example.x3.MovieManagementApp.dtos.UserDtos.UserLoginDto;
import com.example.x3.MovieManagementApp.dtos.UserDtos.UserSignUpDto;
import com.example.x3.MovieManagementApp.entities.User;
import com.example.x3.MovieManagementApp.repositories.UserRepository;
import com.example.x3.MovieManagementApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public ResponseEntity<?> createNewUser(UserSignUpDto userSignUpDto) {
        if (userRepository.existsByDisplayName(userSignUpDto.getDisplayName())) {
            return new ResponseEntity<>("That username is taken. Try another.", HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(userSignUpDto.getEmail())) {
            return new ResponseEntity<>("There's an account with us with this email address. Sign in instead", HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setFirstName(userSignUpDto.getFirstName());
        user.setLastName(userSignUpDto.getLastName());
        user.setDisplayName(userSignUpDto.getDisplayName());
        user.setEmail(userSignUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(userSignUpDto.getPassword()));

        userRepository.save(user);

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> loginUser(UserLoginDto userLoginDto) {

        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userLoginDto.getDisplayName(), userLoginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(auth);

        return new ResponseEntity<>("User successfully logged in", HttpStatus.OK);
    }


}
