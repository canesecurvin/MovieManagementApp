package com.example.x3.MovieManagementApp.dtos.UserDtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserSignUpDto {
    private String firstName;
    private String lastName;
    private String displayName;
    private String email;
    private String password;
}
