package com.example.x3.MovieManagementApp.controllers;


import com.example.x3.MovieManagementApp.dtos.UserDtos.UserSignUpDto;
import com.example.x3.MovieManagementApp.repositories.UserRepository;
import com.example.x3.MovieManagementApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserSignUpDto userSignUpDto){
        return userService.createNewUser(userSignUpDto);
    }

}
