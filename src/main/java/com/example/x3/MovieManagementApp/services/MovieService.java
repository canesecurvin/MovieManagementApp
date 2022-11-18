package com.example.x3.MovieManagementApp.services;

import com.example.x3.MovieManagementApp.entities.Movies;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface MovieService {

    List<Movies> findAllByMovieName(String name);

    List<Movies> findAllByReleaseYear(int year, Sort sort);

    Movies findById(long id);

    List<Movies> findAll(Sort sort);

    void save(Movies movie);

    void deleteById(long id);

}
