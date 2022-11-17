package com.example.x3.MovieManagementApp.controllers;

import com.example.x3.MovieManagementApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/api/v1")
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserSignUpDto userSignUpDto){

        //verify username against repo to prevent multiple user registration with same creds
        if(userRepository.existsByUsername(userSignUpDto.getUsername())){
            return new ResponseEntity<>("That username is taken. Try another.", HttpStatus.BAD_REQUEST);
        }

        //verify username against repo to prevent multiple user registration with same creds
        if(userRepository.existsByEmail(userSignUpDto.getEmail())){
            return new ResponseEntity<>("There's an account with us with this email address. Sign in instead", HttpStatus.BAD_REQUEST);
        }

        //Post-passed checks against repo
        UserEntity user = new UserEntity();
        user.setName(UserSignUpDto.getName());
        user.setUsername(UserSignUpDto.getUsername());
        user.setEmail(UserSignUpDto.getEmail());
        user.setPassword() //Bcrypt encode password before mapping to entity

        userRepository.save(user);

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);

    }
}
