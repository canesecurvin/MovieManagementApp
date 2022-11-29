package com.example.x3.MovieManagementApp.services;

import com.example.x3.MovieManagementApp.dtos.RatingDtos.RatingAddDto;
import com.example.x3.MovieManagementApp.dtos.RatingDtos.RatingDto;
import com.example.x3.MovieManagementApp.entities.Ratings;
import com.example.x3.MovieManagementApp.entities.RatingsPK;

import java.util.List;

public interface RatingService {

    List<Ratings> findAllByMovieId(long movieId);

    List<Ratings> findAllByUserId(long userId);

    Ratings findByRatingsPK(long userId, long movieId);

    List<Ratings> findAll();

    String save(RatingAddDto ratingAddDto);

    String update(RatingDto ratingDto);

    String deleteByRatingsPK(RatingsPK ratingsPK);
}
