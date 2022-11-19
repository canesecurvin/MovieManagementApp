package com.example.x3.MovieManagementApp.controllers;

import com.example.x3.MovieManagementApp.dtos.GenreDtos.GenreAddDto;
import com.example.x3.MovieManagementApp.dtos.GenreDtos.GenreDto;
import com.example.x3.MovieManagementApp.entities.Genres;
import com.example.x3.MovieManagementApp.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("v1/genres")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @GetMapping("/id/{genreId}")
    public Genres findById(@PathVariable long genreId) {
        return genreService.findById(genreId);
    }

    @GetMapping("/{genre}")
    public Optional<Genres> findByGenre(@PathVariable String genre) {
        return genreService.findByGenre(genre);
    }

    @GetMapping("/")
    public List<Genres> findAll() {
        return genreService.findAll();
    }

    @PostMapping("/")
    public String addNewGenre(GenreAddDto genreAddDto) {
        return genreService.save(genreAddDto);
    }

    @PutMapping("/")
    public String updateGenre(GenreDto genreDto) {
        return genreService.updateById(genreDto);
    }

    @DeleteMapping("/{genreId}")
    public String deleteById(@RequestBody long genreId) {

        return genreService.deleteById(genreId);

    }
}
