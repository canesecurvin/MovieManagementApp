package com.example.x3.MovieManagementApp.controllers;

import com.example.x3.MovieManagementApp.dtos.UserDtos.UserLoginDto;
import com.example.x3.MovieManagementApp.dtos.UserDtos.UserPasswordRequestDto;
import com.example.x3.MovieManagementApp.dtos.UserDtos.UserSignUpDto;
import com.example.x3.MovieManagementApp.dtos.UserDtos.UserBasicUpdateRequestDto;
import com.example.x3.MovieManagementApp.repositories.UserRepository;
import com.example.x3.MovieManagementApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*")
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

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody UserLoginDto userLoginDto){
        return userService.loginUser(userLoginDto);
    }

    @PutMapping("user/{id}")
    public ResponseEntity<?> updateBasicUserInfo(@PathVariable Long id, @RequestBody UserBasicUpdateRequestDto userBasicUpdateRequestDto){
        return userService.updateBasicInfo(userBasicUpdateRequestDto, id);
    }


    @PutMapping("pw/{id}")
    public ResponseEntity<?> updatePassword(@PathVariable Long id, @RequestBody UserPasswordRequestDto userPasswordRequestDto){
        return userService.updatePassword(userPasswordRequestDto, id);
    }

}
