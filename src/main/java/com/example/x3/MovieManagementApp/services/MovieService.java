package com.example.x3.MovieManagementApp.services;

import com.example.x3.MovieManagementApp.dtos.MovieDtos.MovieAddDto;
import com.example.x3.MovieManagementApp.dtos.MovieDtos.MovieDto;
import com.example.x3.MovieManagementApp.dtos.MovieDtos.MovieGenreAddDto;
import com.example.x3.MovieManagementApp.dtos.MovieDtos.MovieGenreRemoveDto;
import com.example.x3.MovieManagementApp.entities.Movies;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface MovieService {

    List<Movies> findAllByMovieName(String name);

    List<Movies> findAllByReleaseYear(int year, Sort sort);

    List<Movies> findAllByGenres(String genre);

    Movies findById(long id);

    List<Movies> findAll(Sort sort);

    Page<Movies> findAll(Pageable pageable);

    Optional<List<Movies>> save(MovieAddDto movieAddDto);

    String updateById(MovieDto movieDto);

    String addGenreToMovie(MovieGenreAddDto movieGenreAddDto);

    String removeGenreFromMovie(MovieGenreRemoveDto movieGenreRemoveDto);

    String deleteById(long id);
}
