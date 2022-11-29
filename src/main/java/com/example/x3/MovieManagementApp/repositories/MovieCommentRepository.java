package com.example.x3.MovieManagementApp.repositories;

import com.example.x3.MovieManagementApp.entities.MovieComments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieCommentRepository extends JpaRepository<MovieComments, Long> {

    List<MovieComments> findAllByMovieId(Long id);

    List<MovieComments> findAllByUserIdAndMovieId(Long userId, Long movieId);

    Optional<MovieComments> findByComment(String comment);
}
