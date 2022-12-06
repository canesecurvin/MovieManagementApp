package com.example.x3.MovieManagementApp.controllers;

import com.example.x3.MovieManagementApp.dtos.MovieCommentsDto.MovieCommentAddDto;
import com.example.x3.MovieManagementApp.dtos.MovieCommentsDto.MovieCommentDto;
import com.example.x3.MovieManagementApp.entities.MovieComments;
import com.example.x3.MovieManagementApp.services.MovieCommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Movie comments endpoints")
@RestController
@RequestMapping("v1/movie-comments")
public class MovieCommentController {

    @Autowired
    MovieCommentService movieCommentService;


    @GetMapping("/id/{commentId}")
    @ApiOperation(value = "Get Movie comment by Comment ID")
    public MovieComments findById(@PathVariable Long commentId) {
        return movieCommentService.findById(commentId);
    }

    @GetMapping("/{movieId}")
    @ApiOperation(value = "Get a list of Movie comments by Movie ID")
    public List<MovieComments> findAllCommentsByMovieId(@PathVariable Long movieId) {
        return movieCommentService.findAllCommentsByMovieId(movieId);
    }

    @GetMapping("/{movieId}/{userId}")
    @ApiOperation(value = "Get a list of User's Movie comments by Movie and User IDs")
    public List<MovieComments> findAllCommentsByUserIdAndMovieId(@PathVariable Long userId, @PathVariable Long movieId) {
        return movieCommentService.findAllCommentsByUserIdAndMovieId(userId, movieId);
    }

    @GetMapping("/")
    @ApiOperation(value = "Get a list of all Movie comments")
    public List<MovieComments> findAll() {
        return movieCommentService.findAll();
    }

    @PostMapping("/")
    @ApiOperation(value = "Add new Movie comment")
    public String addNewComment(@RequestBody MovieCommentAddDto movieCommentAddDto) {
        return movieCommentService.saveComment(movieCommentAddDto);
    }

    @PutMapping("/")
    @ApiOperation(value = "Update Movie comment")
    public String updateComment(@RequestBody MovieCommentDto movieCommentDto) {
        return movieCommentService.update(movieCommentDto);
    }

    @DeleteMapping("/{commentId}")
    @ApiOperation(value = "Delete Movie comment by Comment ID")
    public String deleteById(@PathVariable Long commentId) {

        return movieCommentService.deleteById(commentId);
    }
}
