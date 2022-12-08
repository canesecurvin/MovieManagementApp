package com.example.x3.MovieManagementApp.services;

import com.example.x3.MovieManagementApp.dtos.FavoritesDto.FavoritesAddDto;
import com.example.x3.MovieManagementApp.dtos.FavoritesDto.FavoritesResponseDto;
import com.example.x3.MovieManagementApp.entities.Favorites;

import java.util.List;

public interface FavoritesService {
    List<FavoritesResponseDto> userFavorites(Long userId);

    Boolean userFavoriteByUserAndMovie(Long userId, Long movieId);

    List<FavoritesResponseDto> addUserFavorite(FavoritesAddDto favoritesAddDto);

    List<FavoritesResponseDto> deleteUserFavorite(Long favoriteId, Long userId);

    List<FavoritesResponseDto> deleteUserFavoriteByUserAndMovie(Long movieId, Long userId);

    String deleteAll();
}
