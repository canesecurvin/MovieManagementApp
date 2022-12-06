package com.example.x3.MovieManagementApp.controllers;

import com.example.x3.MovieManagementApp.dtos.RatingDtos.RatingAddDto;
import com.example.x3.MovieManagementApp.dtos.RatingDtos.RatingDto;
import com.example.x3.MovieManagementApp.entities.Ratings;
import com.example.x3.MovieManagementApp.entities.RatingsPK;
import com.example.x3.MovieManagementApp.services.RatingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/ratings")
@Api(value = "Movie Rating controller endpoints")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @ApiOperation(value = "Get a list of Movie Ratings by Movie ID")
    @GetMapping("/movie/{movieId}")
    public List<Ratings> findAllByMovieId(@PathVariable long movieId) {
        return ratingService.findAllByMovieId(movieId);
    }

    @ApiOperation(value = "Get a list of Movie Ratings by User ID")
    @GetMapping("/user/{userId}")
    public List<Ratings> findAllByUserId(@PathVariable long userId) {
        return ratingService.findAllByUserId(userId);
    }

    @ApiOperation(value = "Get a Movie Rating given by a User by their User ID")
    @GetMapping("/user/{userId}/{movieId}")
    public Ratings findByCompositeId(@PathVariable long userId, @PathVariable long movieId) {
        return ratingService.findByRatingsPK(userId, movieId);
    }

    @ApiOperation(value = "Get all Movie Ratings")
    @GetMapping("/")
    public List<Ratings> findAll() {
        return ratingService.findAll();
    }

    @ApiOperation(value = "Post a new Movie Rating")
    @PostMapping("/")
    public String addNewRating(@RequestBody RatingAddDto ratingAddDto) {
        return ratingService.save(ratingAddDto);
    }

    @ApiOperation(value = "Update a Movie Rating")
    @PutMapping("/")
    public String updateRating(@RequestBody RatingDto ratingDto) {
        return ratingService.update(ratingDto);
    }

    @ApiOperation(value = "Delete a User's Movie Rating by Movie ID and User ID")
    @DeleteMapping("/{userId}/{movieId}")
    public String deleteRating(@PathVariable long userId, @PathVariable long movieId) {
        return ratingService.deleteByRatingsPK(new RatingsPK(userId, movieId));
    }
}
