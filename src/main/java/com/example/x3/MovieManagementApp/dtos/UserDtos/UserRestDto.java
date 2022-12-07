package com.example.x3.MovieManagementApp.dtos.UserDtos;

import com.example.x3.MovieManagementApp.dtos.SecurityDtos.JwtAuthDto;
import com.example.x3.MovieManagementApp.entities.Movies;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRestDto {
    private String accessToken;
    private String tokenType;
    private int expiresIn;
    private Long id;
    private String email;
    private String displayName;
    private String firstName;
    private String lastName;
    private Set<Movies> movieFavorites;
}
