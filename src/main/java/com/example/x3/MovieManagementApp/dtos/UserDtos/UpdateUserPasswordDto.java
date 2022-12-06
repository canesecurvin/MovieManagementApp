package com.example.x3.MovieManagementApp.dtos.UserDtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserPasswordDto {
    private String oldPassword;
    private String newPassword;
}
