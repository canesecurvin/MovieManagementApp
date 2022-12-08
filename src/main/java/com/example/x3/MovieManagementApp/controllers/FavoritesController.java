package com.example.x3.MovieManagementApp.controllers;

import com.example.x3.MovieManagementApp.dtos.FavoritesDto.FavoritesAddDto;
import com.example.x3.MovieManagementApp.dtos.FavoritesDto.FavoritesResponseDto;
import com.example.x3.MovieManagementApp.services.FavoritesService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api(value = "Movie Favorites Enpoint")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("v1/favorites")
public class FavoritesController {
    @Autowired
    FavoritesService favoritesService;

    @GetMapping("/{userId}")
    public List<FavoritesResponseDto> userFavorites(@PathVariable("userId") Long userId){
        return favoritesService.userFavorites(userId);
    }

    @GetMapping("/{userId}/movie/{movieId}")
    public Boolean userFavorites(@PathVariable("userId") Long userId, @PathVariable("movieId") Long movieId){
        return favoritesService.userFavoriteByUserAndMovie(userId, movieId);
    }

    @PostMapping("")
    public List<FavoritesResponseDto> addUserFavorite(@RequestBody FavoritesAddDto favoritesAddDto){
        System.out.println(favoritesAddDto.getUserId());
        return favoritesService.addUserFavorite(favoritesAddDto);
    }

    @DeleteMapping("/{favoritesId}/user/{userId}")
    public List<FavoritesResponseDto> deleteUserFavorite(@PathVariable("userId") Long userId, @PathVariable("favoritesId") Long favoritesId){
        try {
            return favoritesService.deleteUserFavorite(favoritesId, userId);
        }catch(Exception e) {
            System.out.println(e);
        }
        return new ArrayList<>();
    }

    @DeleteMapping("/movie/{movieId}/user/{userId}")
    public List<FavoritesResponseDto> deleteUserFavoriteByUserAndMovie(@PathVariable("userId") Long userId, @PathVariable("movieId") Long movieId){
        try {
            return favoritesService.deleteUserFavoriteByUserAndMovie(movieId, userId);
        }catch(Exception e) {
            System.out.println(e);
        }
        return new ArrayList<>();
    }
}
