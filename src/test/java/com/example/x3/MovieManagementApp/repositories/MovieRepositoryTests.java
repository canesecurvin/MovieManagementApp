package com.example.x3.MovieManagementApp.repositories;

import com.example.x3.MovieManagementApp.entities.Genres;
import com.example.x3.MovieManagementApp.entities.Movies;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;


@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MovieRepositoryTests {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    GenreRepository genreRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    void saveTest() {
        Movies movieOne = Movies.builder()
                .movieName("Test Movie")
                .movieLength(120)
                .releaseYear(2000)
                .rating(5)
                .build();

        Genres tempGenre = new Genres();
        tempGenre.setGenre("Action");

        genreRepository.save(tempGenre);

        movieOne.addGenre(tempGenre);

        movieRepository.save(movieOne);

        Assertions.assertThat(movieOne.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    void findByIdTest() {
        Movies tempMovie = movieRepository.findById(1L).get();

        Assertions.assertThat(tempMovie.getId()).isEqualTo(1L);
    }

    @Test
    @Order(3)
    @Rollback(value = false)
    void updateTest() {
        Movies tempMovie = movieRepository.findById(1L).get();

        tempMovie.setRating(1);

        Movies updatedMovie = movieRepository.save(tempMovie);

        Assertions.assertThat(updatedMovie.getRating()).isEqualTo(1);

    }

    @Test
    @Order(4)
    void findAllByMovieNameTest() {

        Movies movieTwo = Movies.builder()
                .movieName("Test Movie")
                .movieLength(120)
                .releaseYear(2001)
                .rating(5)
                .build();

        movieRepository.save(movieTwo);

        List<Movies> tempList = movieRepository.findAllByMovieName("Test Movie");

        Assertions.assertThat(tempList.size()).isEqualTo(2);

    }

    @Test
    @Order(5)
    void findAllByReleaseYearTest() {
        Movies movieThree = Movies.builder()
                .movieName("New Movie")
                .movieLength(120)
                .releaseYear(2000)
                .rating(5)
                .build();

        Genres tempGenre = genreRepository.findByGenre("Action").get();

        movieThree.addGenre(tempGenre);

        movieRepository.save(movieThree);

        List<Movies> tempList = movieRepository.findAllByReleaseYear(
                2000, Sort.by(Sort.Direction.ASC, "movieName"));

        Assertions.assertThat(tempList.size()).isEqualTo(2);
    }

    @Test
    @Order(6)
    void findAllByGenresTest() {
        Movies movieFour = Movies.builder()
                .movieName("Genre Movie")
                .movieLength(120)
                .releaseYear(2000)
                .rating(5)
                .build();

        Genres tempGenre = genreRepository.findByGenre("Action").get();

        movieFour.addGenre(tempGenre);

        movieRepository.save(movieFour);

        List<Movies> tempList = movieRepository.findAllByGenres(tempGenre);

        Assertions.assertThat(tempList.size()).isEqualTo(2);
    }

    @Test
    @Order(7)
    @Rollback(value = false)
    void deleteTest() {
        Movies tempMovie = movieRepository.findById(1L).get();

        movieRepository.deleteById(tempMovie.getId());

        Optional<Movies> checkMovie = movieRepository.findById(1L);

        Assertions.assertThat(checkMovie).isEmpty();
    }
}