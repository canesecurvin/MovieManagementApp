package com.example.x3.MovieManagementApp.services;

import com.example.x3.MovieManagementApp.dtos.MovieCommentsDto.MovieCommentAddDto;
import com.example.x3.MovieManagementApp.dtos.MovieCommentsDto.MovieCommentDto;
import com.example.x3.MovieManagementApp.entities.MovieComments;

import java.util.List;

public interface MovieCommentService {

    List<MovieComments> findAll();

    MovieComments findById(Long id);

    List<MovieComments> findAllCommentsByMovieId(Long id);

    String saveComment(MovieCommentAddDto movieCommentAddDto);

    String update(MovieCommentDto movieCommentDto);

    String deleteById(Long id);
}
