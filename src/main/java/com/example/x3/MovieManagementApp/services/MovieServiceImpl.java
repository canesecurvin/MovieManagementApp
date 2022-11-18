package com.example.x3.MovieManagementApp.services;

import com.example.x3.MovieManagementApp.entities.Movies;
import com.example.x3.MovieManagementApp.repositories.MovieRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public class MovieServiceImpl implements MovieService{

    @Autowired
    private MovieRepositories movieRepositories;

    @Autowired
    public MovieServiceImpl(MovieRepositories movieRepositories) {
        movieRepositories = movieRepositories;
    }

    @Override
    public List<Movies> findAllByMovieName(String name) {
        return movieRepositories.findAllByMovieName(name);
    }

    @Override
    public List<Movies> findAllByReleaseYear(int year, Sort sort) {
        return movieRepositories.findAllByReleaseYear(year, sort);
    }

    @Override
    public Movies findById(long id) {
        Optional<Movies> tempMovie = movieRepositories.findById(id);

        return tempMovie.orElse(null);

    }

    @Override
    public void save(Movies movie) {
        movieRepositories.save(movie);
    }

    @Override
    public void deleteById(long id) {
        movieRepositories.deleteById(id);
    }

    @Override
    public List<Movies> findAll(Sort sort) {
        return movieRepositories.findAll(sort);
    }

}
