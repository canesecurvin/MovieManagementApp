package com.example.x3.MovieManagementApp.controllers;

import com.example.x3.MovieManagementApp.dtos.GenreDtos.GenreAddDto;
import com.example.x3.MovieManagementApp.dtos.GenreDtos.GenreDto;
import com.example.x3.MovieManagementApp.entities.Genres;
import com.example.x3.MovieManagementApp.services.GenreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api(value = "Movie Genres endpoints")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("v1/genres")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @ApiOperation(value = "Get Movie Genre by Genre ID")
    @GetMapping("/id/{genreId}")
    public Genres findById(@PathVariable long genreId) {
        return genreService.findById(genreId);
    }

    @ApiOperation(value = "Get Movie Genre by Genre name")
    @GetMapping("/{genre}")
    public Optional<Genres> findByGenre(@PathVariable String genre) {
        return genreService.findByGenre(genre);
    }

    @ApiOperation(value = "Get list of all Movie Genres")
    @GetMapping("/")
    public List<Genres> findAll() {
        return genreService.findAll();
    }

    @ApiOperation(value = "Add new Movie Genre")
    @PostMapping("/")
    public String addNewGenre(GenreAddDto genreAddDto) {
        return genreService.save(genreAddDto);
    }

    @ApiOperation(value = "Update Movie Genre by Genre ID")
    @PutMapping("/")
    public String updateGenre(GenreDto genreDto) {
        return genreService.updateById(genreDto);
    }

    @ApiOperation(value = "Delete Movie Genre by Genre ID")
    @DeleteMapping("/{genreId}")
    public String deleteById(@RequestBody long genreId) {

        return genreService.deleteById(genreId);

    }
}
