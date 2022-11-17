package com.example.x3.MovieManagementApp.controllers;

import com.example.x3.MovieManagementApp.entities.Movies;
import com.example.x3.MovieManagementApp.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/id/{movieId}")
    public Movies getMoviesById(@PathVariable long movieId) {
        return movieService.findMoviesById(movieId);
    }

    @GetMapping("/name/{movieName}")
    public List<Movies> getMoviesByName(@PathVariable String name) {
        return movieService.findAllByMovieName(name);
    }

    @GetMapping("/year/{year}")
    public List<Movies> getMoviesByYear(@PathVariable int year) {
        return movieService.findAllByReleaseYear(year, Sort.by(Sort.Direction.ASC, "movieName"));
    }

    @GetMapping("/")
    public List<Movies> getAllMovies() {
        return movieService.findAll(Sort.by(Sort.Direction.ASC, "movieName"));
    }

    @PostMapping("/id/{movieId}")
    public Optional<List<Movies>> addMovie(@RequestBody Movies newMovie) {
        ArrayList<Movies> tempMovieList = (ArrayList<Movies>) movieService.findAllByMovieName(newMovie.getMovieName());
        ArrayList<Movies> returnList = new ArrayList<>();
        if (tempMovieList.contains(newMovie)) {
            for (Movies movie : tempMovieList) {
                if (movie.getMovieName().equalsIgnoreCase(newMovie.getMovieName())
                        && movie.getReleaseYear() == newMovie.getReleaseYear()) {
                    returnList.add(movie);
                }
            }
            return Optional.of(returnList);
        }
        movieService.save(newMovie);
        return Optional.empty();
    }

}
