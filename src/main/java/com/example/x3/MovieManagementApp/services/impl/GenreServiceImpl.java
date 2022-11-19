package com.example.x3.MovieManagementApp.services.impl;

import com.example.x3.MovieManagementApp.dtos.GenreDtos.GenreAddDto;
import com.example.x3.MovieManagementApp.dtos.GenreDtos.GenreDto;
import com.example.x3.MovieManagementApp.entities.Genres;
import com.example.x3.MovieManagementApp.repositories.GenreRepository;
import com.example.x3.MovieManagementApp.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    GenreRepository genreRepository;

    @Autowired
    public GenreServiceImpl(GenreRepository genreRepository) {
        genreRepository = genreRepository;
    }

    @Override
    public List<Genres> findAll() {
        return genreRepository.findAll();
    }

    @Override
    public Optional<Genres> findByGenre(String genre) {

        return genreRepository.findByGenre(genre);
    }

    @Override
    public Genres findById(long id) {
        Optional<Genres> tempGenre = genreRepository.findById(id);

        return tempGenre.orElse(null);
    }

    @Override
    public String save(GenreAddDto genreAddDto) {
        if (genreRepository.findByGenre(genreAddDto.getGenre()).isPresent()) {
            return "Genre already exists.";
        }

        Genres newGenre = new Genres();
        newGenre.setGenre(genreAddDto.getGenre());
        genreRepository.save(newGenre);

        return "Saved";

    }

    @Override
    public String updateById(GenreDto genreDto) {
        Optional<Genres> tempGenre = genreRepository.findById(genreDto.getId());
        if (tempGenre.isEmpty()) {
            return "Genre does not exist.";
        }

        tempGenre.ifPresent(genres -> {
            genres.setGenre(genreDto.getGenre());
            genreRepository.saveAndFlush(genres);
        });

        return "Genre has been updated.";
    }

    @Override
    public String deleteById(long id) {
        Optional<Genres> tempGenre = genreRepository.findById(id);

        if (tempGenre.isPresent()) {
            genreRepository.deleteById(id);
            return "Deleted genre id: " + id;
        }

        return "Could not find genre with id: " + id;

    }
}
