package com.example.x3.MovieManagementApp.services;

import com.example.x3.MovieManagementApp.dtos.MovieDtos.MovieAddDto;
import com.example.x3.MovieManagementApp.dtos.MovieDtos.MovieDto;
import com.example.x3.MovieManagementApp.entities.Genres;
import com.example.x3.MovieManagementApp.entities.Movies;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface MovieService {

    List<Movies> findAllByMovieName(String name);

    List<Movies> findAllByReleaseYear(int year, Sort sort);

    List<Movies> findAllByGenres(String genre);

    Movies findById(long id);

    List<Movies> findAll(Sort sort);

    Optional<List<Movies>> save(MovieAddDto movieAddDto);

    String updateById(MovieDto movieDto);

    void deleteById(long id);
    //TODO update delete method to return String message
}
