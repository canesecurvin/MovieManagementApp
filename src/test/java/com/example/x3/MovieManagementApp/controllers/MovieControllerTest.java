package com.example.x3.MovieManagementApp.controllers;

import com.example.x3.MovieManagementApp.dtos.MovieDtos.MovieAddDto;
import com.example.x3.MovieManagementApp.dtos.MovieDtos.MovieDto;
import com.example.x3.MovieManagementApp.dtos.MovieDtos.MovieGenreAddDto;
import com.example.x3.MovieManagementApp.entities.Movies;
import com.example.x3.MovieManagementApp.repositories.UserRepository;
import com.example.x3.MovieManagementApp.services.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@AutoConfigureMockMvc(addFilters = false)
class MovieControllerTest {

    @MockBean
    private MovieService movieService;

    @MockBean
    private GenreService genreService;

    @MockBean
    private MovieCommentService movieCommentService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserService userService;

    @MockBean
    private RatingService ratingService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Movies movieOne;
    private Movies movieTwo;
    private  Movies movieThree;
    private MovieDto tempMovieDto;
    private List<Movies> tempMovies = new ArrayList<>();

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
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

        movieThree = Movies.builder()
                .id(3L)
                .movieName("Third Movie")
                .movieLength(100)
                .releaseYear(2001)
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

    @Test
    void getMoviesById() throws Exception {
        when(movieService.findById(anyLong())).thenReturn(movieOne);

        this.mockMvc.perform(get("/v1/movies/id/{movieId}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.movieName", is(movieOne.getMovieName())))
                .andExpect(jsonPath("$.movieLength", is(movieOne.getMovieLength())));
    }

    @Test
    void getMoviesByName() throws Exception {
        when(movieService.findAllByMovieName(anyString())).thenReturn(tempMovies);

        this.mockMvc.perform(get("/v1/movies/name/{movieName}", "Test Movie"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(tempMovies.size())));
    }

    @Test
    void getMoviesByYear() throws Exception {
        when(movieService.findAllByReleaseYear(anyInt(), any(Sort.class))).thenReturn(tempMovies);

        this.mockMvc.perform(get("/v1/movies/year/{year}", 2000))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(tempMovies.size())));
    }

    @Test
    void getMoviesByGenre() throws Exception {
        when(movieService.findAllByGenres(anyString())).thenReturn(tempMovies);

        this.mockMvc.perform(get("/v1/movies/genre/{genre}", "Action"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(tempMovies.size())));
    }

    @Test
    void getAllMoviesSlashPathTest() throws Exception {
        when(movieService.findAll(any(Sort.class))).thenReturn(tempMovies);

        this.mockMvc.perform(get("/v1/movies/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(tempMovies.size())));
    }

    @Test
    void getAllMoviesNoSlashPathTest() throws Exception {
        when(movieService.findAll(any(Sort.class))).thenReturn(tempMovies);

        this.mockMvc.perform(get("/v1/movies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(tempMovies.size())));
    }

    @Test
    void addMovieTest() throws Exception {
        List<Movies> tempSaveList = new ArrayList<>();
        MovieAddDto movieOneAddDto = MovieAddDto.builder()
                .movieName("Test Movie")
                .releaseYear(2000)
                .movieLength(120)
                .rating(5)
                .build();

        when(movieService.save(any(MovieAddDto.class))).thenReturn(Optional.of(tempSaveList));

        this.mockMvc.perform(post("/v1/movies/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(movieOneAddDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(0)));
    }

    @Test
    void addMovieAlreadyExistsTest() throws Exception {
        List<Movies> tempSaveList = new ArrayList<>();
        tempSaveList.add(movieOne);
        MovieAddDto movieOneAddDto = MovieAddDto.builder()
                .movieName("Test Movie")
                .releaseYear(2000)
                .movieLength(120)
                .rating(5)
                .build();

        when(movieService.save(any(MovieAddDto.class))).thenReturn(Optional.of(tempSaveList));

        this.mockMvc.perform(post("/v1/movies/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(movieOneAddDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(1)))
                .andExpect(jsonPath("$.[0].movieName", is(movieOne.getMovieName())))
                .andExpect(jsonPath("$.[0].releaseYear", is(movieOne.getReleaseYear())));
    }

    @Test
    void updateMovieTest() throws Exception {
        when(movieService.updateById(any(MovieDto.class))).thenReturn("Movie Updated.");

        this.mockMvc.perform(put("/v1/movies/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tempMovieDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is("Movie Updated.")));
    }

    @Test
    void addMovieGenreTest() throws Exception{
        MovieGenreAddDto tempMovieGenreAddDto = MovieGenreAddDto.builder()
                .movieId(1)
                .build();

        when(movieService.addGenreToMovie(any(MovieGenreAddDto.class))).thenReturn("Genre(s) have been added.");

        this.mockMvc.perform(put("/v1/movies/genre")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tempMovieGenreAddDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is("Genre(s) have been added.")));
    }

    @Test
    void deleteByIdTest() throws Exception{
        when(movieService.deleteById(anyLong())).thenReturn("Deleted movie id: 1");

        this.mockMvc.perform(delete("/v1/movies/{movieId}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content("1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is("Deleted movie id: 1")));
    }
}