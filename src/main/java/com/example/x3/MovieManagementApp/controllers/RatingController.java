package com.example.x3.MovieManagementApp.controllers;

import com.example.x3.MovieManagementApp.dtos.RatingDtos.RatingAddDto;
import com.example.x3.MovieManagementApp.dtos.RatingDtos.RatingDto;
import com.example.x3.MovieManagementApp.entities.Ratings;
import com.example.x3.MovieManagementApp.entities.RatingsPK;
import com.example.x3.MovieManagementApp.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @GetMapping("/movie/{movieId}")
    public List<Ratings> findAllByMovieId(@PathVariable long movieId) {
        return ratingService.findAllByMovieId(movieId);
    }

    @GetMapping("/user/{userId}")
    public List<Ratings> findAllByUserId(@PathVariable long userId) {
        return ratingService.findAllByUserId(userId);
    }

    @GetMapping("/user/{userId}/{movieId}")
    public Ratings findByCompositeId(@PathVariable long userId, @PathVariable long movieId) {
        return ratingService.findByRatingsPK(userId, movieId);
    }

    @GetMapping("/")
    public List<Ratings> findAll() {
        return ratingService.findAll();
    }

    @PostMapping("/")
    public String addNewRating(RatingAddDto ratingAddDto) {
        return ratingService.save(ratingAddDto);
    }

    @PutMapping("/")
    public String updateRating(RatingDto ratingDto) {
        return ratingService.update(ratingDto);
    }

    @DeleteMapping("/{userId}/{movieId}")
    public String deleteRating(@PathVariable long userId, @PathVariable long movieId) {
        return ratingService.deleteByRatingsPK(new RatingsPK(userId, movieId));
    }
}
