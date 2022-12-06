package com.example.x3.MovieManagementApp.integration;

import com.example.x3.MovieManagementApp.dtos.MovieDtos.MovieDto;
import com.example.x3.MovieManagementApp.entities.Genres;
import com.example.x3.MovieManagementApp.entities.Movies;
import com.example.x3.MovieManagementApp.repositories.MovieRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MoviesIntegrationTest {

    @LocalServerPort
    private int port;

    private String baseUrl = "http://localhost";

    private static RestTemplate restTemplate;

    @Autowired
    private MovieRepository movieRepository;

    private Movies movieOne;
    private Movies movieTwo;
    private  Movies movieThree;
    private Genres genre;
    private MovieDto tempMovieDto;
    private HashSet<Genres> genreSet = new HashSet<>();

    private List<Movies> tempMovies = new ArrayList<>();

    @BeforeAll
    public static void setup() {
        restTemplate = new RestTemplate();
    }

    @BeforeEach
    public void init() {
        baseUrl = baseUrl + ":" + port + "/v1/movies";

        movieOne = Movies.builder()
                .id(1L)
                .movieName("Test Movie")
                .movieLength(120)
                .releaseYear(2000)
                .rating(5)
                .build();
        movieTwo = Movies.builder()
                .id(2L)
                .movieName("Test Movie")
                .movieLength(90)
                .releaseYear(2001)
                .rating(1)
                .build();

        genre = Genres.builder()
                .id(1L)
                .genre("Action")
                .build();

        genreSet.add(genre);

        movieThree = Movies.builder()
                .id(3L)
                .movieName("Third Movie")
                .movieLength(100)
                .releaseYear(2001)
                .genres(genreSet)
                .rating(3)
                .build();



        tempMovieDto = MovieDto.builder()
                .id(1L)
                .movieName("Test Movie")
                .releaseYear(2000)
                .movieLength(120)
                .rating(5)
                .build();

        tempMovies.add(movieOne);
        tempMovies.add(movieTwo);
        tempMovies.add(movieThree);
    }

//    @AfterEach
//    public void breakdown() {
//
//    }

//    @Test
//    void addMovieTest() {
//        Optional<Movies> tempMovie = restTemplate.postForObject(baseUrl + "/", movieOne, Optional.class);
//
//        assertTrue(tempMovie.isEmpty());
//    }
}
