package com.example.x3.MovieManagementApp.services;

import com.example.x3.MovieManagementApp.dtos.GenreDtos.GenreAddDto;
import com.example.x3.MovieManagementApp.dtos.GenreDtos.GenreDto;
import com.example.x3.MovieManagementApp.entities.Genres;

import java.util.List;
import java.util.Optional;

public interface GenreService {

    List<Genres> findAll();

    Optional<Genres> findByGenre(String genre);

    Genres findById(long id);

    String save(GenreAddDto genreAddDto);

    String updateById(GenreDto genreDto);

    String deleteById(long id);

}
