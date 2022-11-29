package com.example.x3.MovieManagementApp.repositories;

import com.example.x3.MovieManagementApp.entities.Ratings;
import com.example.x3.MovieManagementApp.entities.RatingsPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Ratings, RatingsPK> {

    List<Ratings> findAllByMovieId(long movieId);

    List<Ratings> findAllByUserId(long userId);
}
