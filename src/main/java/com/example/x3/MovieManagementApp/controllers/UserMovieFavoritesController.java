package com.example.x3.MovieManagementApp.controllers;

import com.example.x3.MovieManagementApp.dtos.UserMovieFavoritesDto.UserMovieFavoritesAddDto;
import com.example.x3.MovieManagementApp.entities.Movies;
import com.example.x3.MovieManagementApp.entities.UserMovieFavorites;
import com.example.x3.MovieManagementApp.services.UserMovieFavoritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("v1/user-movies")
public class UserMovieFavoritesController {

    @Autowired
    private UserMovieFavoritesService userMovieFavoritesService;


    @GetMapping("/user/{userId}")
    public List<Movies> findAllFavoritesForUser(@PathVariable long userId) {

        return userMovieFavoritesService.findAllFavoritesForUser(userId);
    }

    @GetMapping("/")
    public List<UserMovieFavorites> findAll() {
        return userMovieFavoritesService.findAll();
    }

    @GetMapping("/user/{userId}/{movieId}")
    public UserMovieFavorites findByUserAndMovie(@PathVariable long userId, @PathVariable long movieId) {
        return userMovieFavoritesService.findByUserAndMovie(userId, movieId);
    }

    @PostMapping("/")
    public String updateFavoriteMovie(@RequestBody UserMovieFavoritesAddDto userMovieFavoritesAddDto) {
        return userMovieFavoritesService.updateFavorite(userMovieFavoritesAddDto);

    }


}
