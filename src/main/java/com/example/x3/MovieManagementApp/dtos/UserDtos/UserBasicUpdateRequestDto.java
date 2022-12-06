package com.example.x3.MovieManagementApp.dtos.UserDtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserBasicUpdateRequestDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String displayName;
    private String email;
}