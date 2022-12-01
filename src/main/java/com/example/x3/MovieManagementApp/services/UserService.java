package com.example.x3.MovieManagementApp.services;

import com.example.x3.MovieManagementApp.dtos.UserDtos.UserLoginDto;
import com.example.x3.MovieManagementApp.dtos.UserDtos.UserSignUpDto;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<?> createNewUser(UserSignUpDto userSignUpDto);

    ResponseEntity<?> loginUser(UserLoginDto userLoginDto);
}
