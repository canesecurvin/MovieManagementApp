package com.example.x3.MovieManagementApp.dtos.FavoritesDto;

import com.example.x3.MovieManagementApp.entities.Favorites;
import com.example.x3.MovieManagementApp.entities.Movies;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FavoritesResponseDto{
    private Long id;
    private Long userId;
    private Movies movie;

    public FavoritesResponseDto(Favorites favorites){
        this.id = favorites.getId();
        this.userId = favorites.getUser().getId();
        this.movie = favorites.getMovie();
    }
}
