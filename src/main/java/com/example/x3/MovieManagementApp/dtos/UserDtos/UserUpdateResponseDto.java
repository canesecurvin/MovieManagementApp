package com.example.x3.MovieManagementApp.dtos.UserDtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserUpdateResponseDto {
    private String firstName;
    private String lastName;
    private String displayName;
    private String email;
    private String updateMessage;
}
