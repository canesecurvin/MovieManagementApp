package com.example.x3.MovieManagementApp.dtos.UserDtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserMovieFavoritesRemoveDto {

    private long userId;
    private List<Long> movieFavoritesId;
}
