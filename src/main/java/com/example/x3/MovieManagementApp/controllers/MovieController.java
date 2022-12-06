package com.example.x3.MovieManagementApp.controllers;

import com.example.x3.MovieManagementApp.dtos.MovieDtos.MovieAddDto;
import com.example.x3.MovieManagementApp.dtos.MovieDtos.MovieDto;
import com.example.x3.MovieManagementApp.dtos.MovieDtos.MovieGenreAddDto;
import com.example.x3.MovieManagementApp.entities.Movies;
import com.example.x3.MovieManagementApp.services.MovieService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/movies")
@Api(value = "Movie controller endpoints")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @ApiOperation(value = "Get Movie by Movie ID")
    @GetMapping("/id/{movieId}")
    public Movies getMoviesById(@PathVariable long movieId) {
        return movieService.findById(movieId);
    }

    @ApiOperation(value = "Get list of Movies by Movie Name")
    @GetMapping("/name/{movieName}")
    public List<Movies> getMoviesByName(@PathVariable String movieName) {
        return movieService.findAllByMovieName(movieName);
    }

    @ApiOperation(value = "Get list of Movies by Movie release year")
    @GetMapping("/year/{year}")
    public List<Movies> getMoviesByYear(@PathVariable int year) {
        return movieService.findAllByReleaseYear(year, Sort.by(Sort.Direction.ASC, "movieName"));
    }

    @ApiOperation(value = "Get list of Movies by Movie genre")
    @GetMapping("/genre/{genre}")
    public List<Movies> getMoviesByGenre(@PathVariable String genre) {
        return movieService.findAllByGenres(genre);
    }

    @ApiOperation(value = "Get list of all Movies")
    @GetMapping(value = {"/", ""})
    public List<Movies> getAllMovies() {
        return movieService.findAll(Sort.by(Sort.Direction.ASC, "movieName"));
    }

    @ApiOperation(value = "Add a Movie")
    @PostMapping("/")
    public Optional<List<Movies>> addMovie(@RequestBody MovieAddDto newMovie) {

        return movieService.save(newMovie);
    }

    @PutMapping("/")
    @ApiOperation(value = "Update a Movie")
    public String updateMovie(@RequestBody MovieDto movieDto) {
        return movieService.updateById(movieDto);
    }

    @PutMapping("/genre")
    @ApiOperation(value = "Add genre to Movie by its Movie ID")
    public String addMovieGenre(@RequestBody MovieGenreAddDto movieGenreAddDto) {
        return movieService.addGenreToMovie(movieGenreAddDto);
    }

    @DeleteMapping("/{movieId}")
    @ApiOperation(value = "Delete Movie by Movie ID")
    public String deleteById(@RequestBody long movieId) {

        return movieService.deleteById(movieId);

    }
}
