package com.example.x3.MovieManagementApp.services.impl;

import com.example.x3.MovieManagementApp.dtos.MovieCommentsDto.MovieCommentAddDto;
import com.example.x3.MovieManagementApp.dtos.MovieCommentsDto.MovieCommentDto;
import com.example.x3.MovieManagementApp.entities.MovieComments;
import com.example.x3.MovieManagementApp.entities.Movies;
import com.example.x3.MovieManagementApp.repositories.MovieCommentRepository;
import com.example.x3.MovieManagementApp.repositories.MovieRepository;
import com.example.x3.MovieManagementApp.services.MovieCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class MovieCommentServiceImpl implements MovieCommentService {

    @Autowired
    MovieCommentRepository movieCommentRepository;

    @Autowired
    MovieRepository movieRepository;

    @Override
    public List<MovieComments> findAll() {
        return movieCommentRepository.findAll();
    }

    @Override
    public MovieComments findById(Long id) {
        Optional<MovieComments> tempMovieComment = movieCommentRepository.findById(id);

        return tempMovieComment.orElse(null);
    }

    @Override
    public List<MovieComments> findAllCommentsByMovieId(Long movieId) {
        Optional<Movies> tempMovie = movieRepository.findById(movieId);

        if (tempMovie.isPresent()) {
            return movieCommentRepository.findAllByMovieId(movieId);
        }
        else {
            return Collections.emptyList();
        }
    }

    @Override
    public String saveComment(MovieCommentAddDto movieCommentAddDto) {
        List<MovieComments> commentsByUser = movieCommentAddDto.getUser().getComments();
        for (MovieComments x : commentsByUser) {
            if ((x.getMovie().getId()) == movieCommentAddDto.getMovie().getId()) {
                return "Error. This user has already made comment for this movie. Choose another movie";
            }
        }

        MovieComments newMovieComment = new MovieComments();
        newMovieComment.setComment(movieCommentAddDto.getComment());
        movieCommentRepository.save(newMovieComment);

        return "Comment is saved";
    }

    @Override
    public String update(MovieCommentDto movieCommentDto) {
        Optional<MovieComments> tempMovieComment = movieCommentRepository.findById(movieCommentDto.getId());
        if (tempMovieComment.isEmpty()) {
            return "This Comment does not exist.";
        }

        tempMovieComment.ifPresent(movieComment -> {
            movieComment.setComment(movieCommentDto.getComment());
            movieCommentRepository.saveAndFlush(movieComment);
        });

        return "Comment has been updated.";
    }

    @Override
    public String deleteById(Long id) {
        Optional<MovieComments> tempMovieComment = movieCommentRepository.findById(id);

        if (tempMovieComment.isPresent()) {
            movieCommentRepository.deleteById(id);
            return "Comment with id: " + id + " was deleted";
        }

        return "Could not find this comment with id: " + id;
    }
}
