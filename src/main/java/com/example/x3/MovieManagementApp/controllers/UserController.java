package com.example.x3.MovieManagementApp.controllers;


import com.example.x3.MovieManagementApp.dtos.UserDtos.UserSignUpDto;
import com.example.x3.MovieManagementApp.repositories.UserRepository;
import com.example.x3.MovieManagementApp.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/v1/users")
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserSignUpDto userSignUpDto){

        //verify username against repo to prevent multiple user registration with same creds
        if(userRepository.existsByUsername(userSignUpDto.getDisplayName())){
            return new ResponseEntity<>("That username is taken. Try another.", HttpStatus.BAD_REQUEST);
        }

        //verify username against repo to prevent multiple user registration with same creds
        if(userRepository.existsByEmail(userSignUpDto.getEmail())){
            return new ResponseEntity<>("There's an account with us with this email address. Sign in instead", HttpStatus.BAD_REQUEST);
        }

        //Post-passed checks against repo
        User user = new User();
        user.setFirstName(userSignUpDto.getFirstName());
        user.setLastName(userSignUpDto.getLastName());
        user.setDisplayName(userSignUpDto.getDisplayName());
        user.setEmail(userSignUpDto.getEmail());
        user.setPassword(); //Bcrypt encode password before mapping to entity

        userRepository.save(user);

        //Can send User obj based on front end specification; HTTP 200
        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);

    }

}
