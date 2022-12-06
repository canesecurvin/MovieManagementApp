//package com.example.x3.MovieManagementApp.services;
//
//import com.example.x3.MovieManagementApp.dtos.UserMovieFavoritesDto.UserMovieFavoritesAddDto;
//import com.example.x3.MovieManagementApp.entities.Movies;
//import com.example.x3.MovieManagementApp.entities.User;
//import com.example.x3.MovieManagementApp.entities.UserMovieFavorites;
//import com.example.x3.MovieManagementApp.entities.UserMovieFavoritesPK;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.Optional;
//
//public interface UserMovieFavoritesService {
//    //Commented totally for now but we might be will need it for future expansion
//
//    public List<UserMovieFavorites> findAll();
//
//    public List<Movies> findAllFavoritesForUser(long userId);
//
//    public List<User> findAllUsersWithFavorites(long movieId);
//
//    public UserMovieFavorites findByUserMovieFavoritesPK(long userId, long movieId);
//
//    public String updateFavorite(UserMovieFavoritesAddDto userMovieFavoritesAddDto);
//}
