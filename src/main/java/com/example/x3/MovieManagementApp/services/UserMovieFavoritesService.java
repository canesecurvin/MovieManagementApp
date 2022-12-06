package com.example.x3.MovieManagementApp.services;

import com.example.x3.MovieManagementApp.dtos.UserMovieFavoritesDto.UserMovieFavoritesAddDto;
import com.example.x3.MovieManagementApp.entities.Movies;
import com.example.x3.MovieManagementApp.entities.User;
import com.example.x3.MovieManagementApp.entities.UserMovieFavorites;

import java.util.List;

public interface UserMovieFavoritesService {

    public List<UserMovieFavorites> findAll();

    public List<Movies> findAllFavoritesForUser(long userId);

    public List<User> findAllUsersWithFavorites(long userId);

    public UserMovieFavorites findByUserMovieFavoritesPK(long userId, long movieId);

    public UserMovieFavorites findByUserAndMovie(long userId, long movieId);

    public String updateFavorite(UserMovieFavoritesAddDto userMovieFavoritesAddDto);
}
