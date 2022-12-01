package com.example.x3.MovieManagementApp.services.impl;

import com.example.x3.MovieManagementApp.dtos.RatingDtos.RatingAddDto;
import com.example.x3.MovieManagementApp.dtos.RatingDtos.RatingDto;
import com.example.x3.MovieManagementApp.entities.Movies;
import com.example.x3.MovieManagementApp.entities.Ratings;
import com.example.x3.MovieManagementApp.entities.RatingsPK;
import com.example.x3.MovieManagementApp.entities.User;
import com.example.x3.MovieManagementApp.repositories.MovieRepository;
import com.example.x3.MovieManagementApp.repositories.RatingRepository;
import com.example.x3.MovieManagementApp.repositories.UserRepository;
import com.example.x3.MovieManagementApp.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    RatingRepository ratingRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    UserRepository userRepository;

    public RatingServiceImpl(RatingRepository ratingRepository, MovieRepository movieRepository, UserRepository userRepository) {
        ratingRepository = ratingRepository;
        movieRepository = movieRepository;
        userRepository = userRepository;
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
    @Transactional
    public Ratings findByRatingsPK(long userId, long movieId) {

        RatingsPK ratingsPK = new RatingsPK(userId, movieId);

        return ratingRepository.findById(ratingsPK).orElse(null);
    }

    @Override
    public List<Ratings> findAll() {
        return ratingRepository.findAll();
    }

    @Override
    @Transactional
    public String save(RatingAddDto ratingAddDto) {
        long userId = ratingAddDto.getUserId();
        long movieId = ratingAddDto.getMovieId();
        Optional<String> userMovieCheck = validateUserAndMovie(userId, movieId);

        if (userMovieCheck.isPresent()) {
            return userMovieCheck.get();
        }

        Ratings tempRating = new Ratings();
        RatingsPK tempRatingsPK = new RatingsPK(ratingAddDto.getUserId(), ratingAddDto.getMovieId());

        tempRating.setRatingsPK(tempRatingsPK);
        tempRating.setRating(ratingAddDto.getRating());
        tempRating.setReview(ratingAddDto.getReview());
        ratingRepository.save(tempRating);

        updateMovieRating(movieId);

        return "Rating has been saved.";
    }

    @Override
    @Transactional
    public String update(RatingDto ratingDto) {
        RatingsPK tempRatingPK = new RatingsPK(ratingDto.getUserId(), ratingDto.getMovieId());
        Optional<Ratings> tempRating = ratingRepository.findById(tempRatingPK);

        if (tempRating.isPresent()) {
            ratingRepository.saveAndFlush(tempRating.get());

            long movieId = tempRating.get().getRatingsPK().getMovieId();
            updateMovieRating(movieId);

            return "Rating has been updated.";
        }
        return "Rating could not be found.";
    }

    private Optional<String> validateUserAndMovie(long userId, long movieId) {
        Optional<User> tempUser = userRepository.findById(userId);
        Optional<Movies> tempMovie = movieRepository.findById(movieId);

        if (tempUser.isEmpty() || tempMovie.isEmpty()) {
            StringBuilder tempString = new StringBuilder();
            if (tempUser.isEmpty()) tempString.append("Cannot find userId: ")
                    .append(userId)
                    .append("\n");

            if (tempMovie.isEmpty()) tempString.append("Cannot find movieId: ")
                    .append(movieId);

            return Optional.of(tempString.toString());
        }

        return Optional.empty();
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
    @Transactional
    public String deleteByRatingsPK(RatingsPK ratingsPK) {
        ratingRepository.deleteById(ratingsPK);
        return "Rating has been deleted.";
    }
}
