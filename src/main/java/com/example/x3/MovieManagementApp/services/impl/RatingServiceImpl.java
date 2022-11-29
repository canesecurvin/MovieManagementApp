package com.example.x3.MovieManagementApp.services.impl;

import com.example.x3.MovieManagementApp.dtos.RatingDtos.RatingAddDto;
import com.example.x3.MovieManagementApp.dtos.RatingDtos.RatingDto;
import com.example.x3.MovieManagementApp.entities.Movies;
import com.example.x3.MovieManagementApp.entities.Ratings;
import com.example.x3.MovieManagementApp.entities.RatingsPK;
import com.example.x3.MovieManagementApp.repositories.MovieRepository;
import com.example.x3.MovieManagementApp.repositories.RatingRepository;
import com.example.x3.MovieManagementApp.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    RatingRepository ratingRepository;

    @Autowired
    MovieRepository movieRepository;

    public RatingServiceImpl(RatingRepository ratingRepository, MovieRepository movieRepository) {
        ratingRepository = ratingRepository;
        movieRepository = movieRepository;
    }

    @Override
    public List<Ratings> findAllByMovieId(long movieId) {
        return ratingRepository.findAllByMovieId(movieId);
    }

    @Override
    public List<Ratings> findAllByUserId(long userId) {
        return ratingRepository.findAllByUserId(userId);
    }

    @Override
    public Ratings findByRatingsPK(long userId, long movieId) {

        RatingsPK ratingsPK = new RatingsPK(userId, movieId);

        return ratingRepository.findById(ratingsPK).orElse(null);
    }

    @Override
    public List<Ratings> findAll() {
        return ratingRepository.findAll();
    }

    @Override
    public String save(RatingAddDto ratingAddDto) {
        Ratings tempRating = new Ratings();

        tempRating.setRatingsPK(ratingAddDto.getRatingsPK());
        tempRating.setRating(ratingAddDto.getRating());
        tempRating.setReview(ratingAddDto.getReview());
        ratingRepository.save(tempRating);

        long movieId = tempRating.getRatingsPK().getMovieId();
        updateMovieRating(movieId);

        return "Rating has been saved.";
    }

    @Override
    public String update(RatingDto ratingDto) {
        Optional<Ratings> tempRating = ratingRepository.findById(ratingDto.getRatingsPK());

        if (tempRating.isPresent()) {
            ratingRepository.saveAndFlush(tempRating.get());

            long movieId = tempRating.get().getRatingsPK().getMovieId();
            updateMovieRating(movieId);

            return "Rating has been updated.";
        }
        return "Rating could not be found.";
    }

    private void updateMovieRating(long movieId) {
        List<Ratings> ratingsList = ratingRepository.findAllByMovieId(movieId);
        double ratingAverage = 0;

        for (int i = 0; i < ratingsList.size(); i++) {
            ratingAverage += ratingsList.get(i).getRating();
        }

        ratingAverage /= ratingsList.size();

        Optional<Movies> tempMovie = movieRepository.findById(movieId);

        if (tempMovie.isPresent()) {
            tempMovie.get().setRating(ratingAverage);
            movieRepository.saveAndFlush(tempMovie.get());
        }
    }

    @Override
    public String deleteByRatingsPK(RatingsPK ratingsPK) {
        ratingRepository.deleteById(ratingsPK);
        return "Rating has been deleted.";
    }
}
