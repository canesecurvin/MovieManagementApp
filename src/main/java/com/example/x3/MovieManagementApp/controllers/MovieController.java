package com.example.x3.MovieManagementApp.controllers;

import com.example.x3.MovieManagementApp.dtos.MovieDtos.MovieAddDto;
import com.example.x3.MovieManagementApp.dtos.MovieDtos.MovieDto;
import com.example.x3.MovieManagementApp.entities.Movies;
import com.example.x3.MovieManagementApp.services.MovieService;
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
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/id/{movieId}")
    public Movies getMoviesById(@PathVariable long movieId) {
        return movieService.findById(movieId);
    }

    @GetMapping("/name/{movieName}")
    public List<Movies> getMoviesByName(@PathVariable String name) {
        return movieService.findAllByMovieName(name);
    }

    @GetMapping("/year/{year}")
    public List<Movies> getMoviesByYear(@PathVariable int year) {
        return movieService.findAllByReleaseYear(year, Sort.by(Sort.Direction.ASC, "movieName"));
    }

    @GetMapping("/genre/{genre}")
    public List<Movies> getMoviesByGenre(@PathVariable String genre) {
        return movieService.findAllByGenres(genre);
    }

    @GetMapping(value = {"/", ""})
    public List<Movies> getAllMovies() {
        return movieService.findAll(Sort.by(Sort.Direction.ASC, "movieName"));
    }

    @PostMapping("/id/{movieId}")
    public Optional<List<Movies>> addMovie(@RequestBody MovieAddDto newMovie) {

        return movieService.save(newMovie);
    }

    @PutMapping("/")
    public String updateById(@RequestBody MovieDto movieDto) {
        return movieService.updateById(movieDto);
    }

    @DeleteMapping("/{movieId}")
    public String deleteById(@RequestBody long movieId) {

        return movieService.deleteById(movieId);

    }
}
