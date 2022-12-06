package com.example.x3.MovieManagementApp.controllers;

import com.example.x3.MovieManagementApp.dtos.UserDtos.UpdateUserPasswordDto;
import com.example.x3.MovieManagementApp.dtos.UserDtos.UserLoginDto;
import com.example.x3.MovieManagementApp.dtos.UserDtos.UserSignUpDto;
import com.example.x3.MovieManagementApp.dtos.UserDtos.UserBasicUpdateRequestDto;
import com.example.x3.MovieManagementApp.repositories.UserRepository;
import com.example.x3.MovieManagementApp.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Api(value = "User controller endpoints allow for user registration and login")
@CrossOrigin(origins = "*")
@RequestMapping("/v1/users")
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Endpoint to register new user on Movie App")
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserSignUpDto userSignUpDto){
        return userService.createNewUser(userSignUpDto);
    }

    @ApiOperation(value = "Endpoint to login user on Movie App")
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody UserLoginDto userLoginDto){
        return userService.loginUser(userLoginDto);
    }

    @PutMapping("user/{id}")
    public ResponseEntity<?> updateBasicUserInfo(@PathVariable Long id, @RequestBody UserBasicUpdateRequestDto userBasicUpdateRequestDto){
        return userService.updateBasicInfo(userBasicUpdateRequestDto, id);
    }

    @PutMapping("password/{id}")
    public ResponseEntity<?> updateUserPassword(@PathVariable Long id, @RequestBody UpdateUserPasswordDto updateUserPasswordDto){
        return userService.updateUserPassword(id, updateUserPasswordDto);
    }

}
