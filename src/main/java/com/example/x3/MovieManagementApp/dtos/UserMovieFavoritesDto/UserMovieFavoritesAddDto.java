package com.example.x3.MovieManagementApp.dtos.UserMovieFavoritesDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserMovieFavoritesAddDto {

    private long userId;
    private long movieId;
}
