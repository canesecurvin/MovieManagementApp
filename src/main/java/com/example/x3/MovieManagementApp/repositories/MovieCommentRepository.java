package com.example.x3.MovieManagementApp.repositories;

import com.example.x3.MovieManagementApp.entities.MovieComments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieCommentRepository extends JpaRepository<MovieComments, Long> {

    List<MovieComments> findAllByMovieId(Long id);
}
