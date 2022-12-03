package com.example.x3.MovieManagementApp.services.impl;

import com.example.x3.MovieManagementApp.config.JwtConfig.JwtProvider;
import com.example.x3.MovieManagementApp.dtos.SecurityDtos.JwtAuthDto;
import com.example.x3.MovieManagementApp.dtos.UserDtos.UserLoginDto;
import com.example.x3.MovieManagementApp.dtos.UserDtos.UserSignUpDto;
import com.example.x3.MovieManagementApp.dtos.UserDtos.UserRestDto;
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
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;


    @Override
    public ResponseEntity<?> createNewUser(UserSignUpDto userSignUpDto) {
        if (userRepository.existsByDisplayName(userSignUpDto.getDisplayName())) {
            return new ResponseEntity<>("That display name is taken. Try another.", HttpStatus.BAD_REQUEST);
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

        return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> loginUser(UserLoginDto userLoginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userLoginDto.getEmail(), userLoginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        if (userRepository.existsByEmail(userLoginDto.getEmail())) {
            Long userId = userRepository.findByEmail(userLoginDto.getEmail()).get().getId();
            String displayName = userRepository.findById(userId).get().getDisplayName();
            String email = userRepository.findById(userId).get().getEmail();
            String firstName = userRepository.findById(userId).get().getFirstName();
            String lastName = userRepository.findById(userId).get().getLastName();
            String generatedToken = jwtProvider.generateToken(authentication);

//            String jwt = String.format("%s:%s:%s",generatedToken, displayName, userId);
            UserRestDto userRest = UserRestDto.builder()
                    .id(userId)
                    .displayName(displayName)
                    .email(email)
                    .firstName(firstName)
                    .lastName(lastName)
                    .accessToken(generatedToken)
                    .tokenType("Bearer")
                    .expiresIn(86400)
                    .build();




            return new ResponseEntity<>(userRest, HttpStatus.OK);
        }


        return new ResponseEntity<>("Invalid credentials", HttpStatus.BAD_REQUEST);
    }


}
