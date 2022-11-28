package com.example.x3.MovieManagementApp.controllers;

import com.example.x3.MovieManagementApp.dtos.MovieCommentsDto.MovieCommentAddDto;
import com.example.x3.MovieManagementApp.dtos.MovieCommentsDto.MovieCommentDto;
import com.example.x3.MovieManagementApp.entities.MovieComments;
import com.example.x3.MovieManagementApp.services.MovieCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/movie-comments")
public class MovieCommentController {

    @Autowired
    MovieCommentService movieCommentService;


    @GetMapping("/id/{commentId}")
    public MovieComments findById(@PathVariable Long commentId) {
        return movieCommentService.findById(commentId);
    }

    @GetMapping("/{movieId}")
    public List<MovieComments> findAllCommentsByMovieId(@PathVariable Long movieId) {
        return movieCommentService.findAllCommentsByMovieId(movieId);
    }

    @GetMapping("/")
    public List<MovieComments> findAll() {
        return movieCommentService.findAll();
    }

    @PostMapping("/")
    public String addNewComment(@RequestBody MovieCommentAddDto movieCommentAddDto) {
        return movieCommentService.saveComment(movieCommentAddDto);
    }

    @PutMapping("/")
    public String updateComment(@RequestBody MovieCommentDto movieCommentDto) {
        return movieCommentService.update(movieCommentDto);
    }

    @DeleteMapping("/{commentId}")
    public String deleteById(@PathVariable Long commentId) {

        return movieCommentService.deleteById(commentId);
    }
}
