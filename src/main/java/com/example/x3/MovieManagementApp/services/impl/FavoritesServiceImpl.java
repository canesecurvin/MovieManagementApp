package com.example.x3.MovieManagementApp.services.impl;

import com.example.x3.MovieManagementApp.dtos.FavoritesDto.FavoritesAddDto;
import com.example.x3.MovieManagementApp.dtos.FavoritesDto.FavoritesResponseDto;
import com.example.x3.MovieManagementApp.entities.Favorites;
import com.example.x3.MovieManagementApp.entities.Movies;
import com.example.x3.MovieManagementApp.entities.User;
import com.example.x3.MovieManagementApp.repositories.FavoritesRepository;
import com.example.x3.MovieManagementApp.repositories.MovieRepository;
import com.example.x3.MovieManagementApp.repositories.UserRepository;
import com.example.x3.MovieManagementApp.services.FavoritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FavoritesServiceImpl implements FavoritesService {
    @Autowired
    FavoritesRepository favoritesRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MovieRepository movieRepository;

    @Override
    public List<FavoritesResponseDto> userFavorites(Long userId){
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()){
            List<Favorites> favorites = favoritesRepository.findAllByUserId(userId);
            return favorites.stream().map(fav -> {
                Optional<Movies> moviesOptional = movieRepository.findById(fav.getMovie().getId());
                Movies movie = new Movies();
                if (moviesOptional.isPresent()){
                    movie = new Movies(moviesOptional.get());
                }
                return new FavoritesResponseDto(fav);
            }).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    public Boolean userFavoriteByUserAndMovie(Long userId, Long movieId){
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Movies> moviesOptional = movieRepository.findById(movieId);
        if (userOptional.isPresent() && moviesOptional.isPresent()){
            Optional<Favorites> favorite = favoritesRepository.findByUserAndMovie(userOptional.get(), moviesOptional.get());
            if (favorite.isPresent())return true;
        }
        return false;
    }

    @Override
    @Transactional
    public List<FavoritesResponseDto> addUserFavorite(FavoritesAddDto favoritesAddDto){
        Optional<User> userOptional = userRepository.findById(favoritesAddDto.getUserId());
        Optional<Movies> moviesOptional = movieRepository.findById(favoritesAddDto.getMovieId());
        if (moviesOptional.isPresent()) System.out.println(moviesOptional.get().toString());
        if (userOptional.isPresent()){
            Favorites favorite = new Favorites(userOptional.get(), moviesOptional.get());
            favoritesRepository.save(favorite);
        }
        return this.userFavorites(favoritesAddDto.getUserId());
    }

    @Override
    @Transactional
    public List<FavoritesResponseDto> deleteUserFavorite(Long favoriteId, Long userId){
        favoritesRepository.deleteById(favoriteId);
        return this.userFavorites(userId);
    }

    @Override
    @Transactional
    public List<FavoritesResponseDto> deleteUserFavoriteByUserAndMovie(Long movieId, Long userId){
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Movies> moviesOptional = movieRepository.findById(movieId);
        if (userOptional.isPresent() && moviesOptional.isPresent()){
            favoritesRepository.deleteDistinctByUserAndMovie(userOptional.get(), moviesOptional.get());
        }
        return this.userFavorites(userId);
    }

    @Override
    public String deleteAll(){
        favoritesRepository.deleteAll();
        return "ALL DELETED!";
    }
}
