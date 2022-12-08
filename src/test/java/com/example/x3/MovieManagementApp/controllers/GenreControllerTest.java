package com.example.x3.MovieManagementApp.controllers;

import com.example.x3.MovieManagementApp.entities.Genres;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@AutoConfigureMockMvc(addFilters = false)
class GenreControllerTest {

    @MockBean
    private GenreService genreService;

    @MockBean
    private MovieService movieService;

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

    private String baseUrl = "/v1/genres";

    private Genres genreOne;
    private Genres genreTwo;
    private List<Genres> tempGenres = new ArrayList<>();

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        genreOne = Genres.builder()
                .id(1L)
                .genre("Action")
                .build();

        genreTwo = Genres.builder()
                .id(2L)
                .genre("Fantasy")
                .build();

        tempGenres.add(genreOne);
        tempGenres.add(genreTwo);
    }

    @Test
    void findByIdTest() throws Exception {
//        when(genreService.findById(anyLong())).thenReturn(genreOne);
//
//        this.mockMvc.perform(get(baseUrl+"/{genreId}", 1L))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.genre", is(genreOne.getGenre())))
//                .andExpect(jsonPath("$.id", is(genreOne.getId())));
    }

    @Test
    void findByGenre() {
    }

    @Test
    void findAll() {
    }

    @Test
    void addNewGenre() {
    }

    @Test
    void updateGenre() {
    }

    @Test
    void deleteById() {
    }
}