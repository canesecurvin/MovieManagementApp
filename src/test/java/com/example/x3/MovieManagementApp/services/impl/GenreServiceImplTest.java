package com.example.x3.MovieManagementApp.services.impl;

import com.example.x3.MovieManagementApp.dtos.GenreDtos.GenreAddDto;
import com.example.x3.MovieManagementApp.dtos.GenreDtos.GenreDto;
import com.example.x3.MovieManagementApp.entities.Genres;
import com.example.x3.MovieManagementApp.repositories.GenreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GenreServiceImplTest {

    @InjectMocks
    private GenreServiceImpl genreServiceImpl;

    @Mock
    private GenreRepository genreRepository;

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
    void findAll() {
        when(genreRepository.findAll()).thenReturn(tempGenres);

        List<Genres> foundGenres = genreServiceImpl.findAll();

        assertEquals(2, foundGenres.size());
        assertEquals(1L, foundGenres.get(0).getId());
        assertEquals(2L, foundGenres.get(1).getId());
    }

    @Test
    void findByGenreTest() {
        when(genreRepository.findByGenre(anyString())).thenReturn(Optional.of(genreOne));

        Optional<Genres> foundGenre = genreRepository.findByGenre("Action");

        assertTrue(foundGenre.isPresent());
        assertEquals("Action", foundGenre.get().getGenre());
    }

    @Test
    void findByIdTest() {
        when(genreRepository.findById(anyLong())).thenReturn(Optional.of(genreOne));

        Genres foundGenre = genreServiceImpl.findById(1L);
        assertNotEquals(null, foundGenre);
        assertEquals(1L, foundGenre.getId());
    }

    @Test
    void saveAlreadyExistsTest() {
        when(genreRepository.findByGenre(anyString())).thenReturn(Optional.of(genreOne));

        GenreAddDto tempGenreAddDto = new GenreAddDto("Action");

        String result = genreServiceImpl.save(tempGenreAddDto);

        verify(genreRepository, times(0)).save(any(Genres.class));
        assertEquals("Genre already exists.", result);

    }

    @Test
    void saveNewTest() {
        when(genreRepository.findByGenre(anyString())).thenReturn(Optional.empty());

        GenreAddDto tempGenreAddDto = new GenreAddDto("Adventure");

        String result = genreServiceImpl.save(tempGenreAddDto);

        verify(genreRepository, times(1)).save(any(Genres.class));
        assertEquals("Saved", result);
    }

    @Test
    void updateByIdExistsTest() {
        when(genreRepository.findById(anyLong())).thenReturn(Optional.of(genreOne));

        GenreDto tempGenreDto = new GenreDto(1L, "Action");

        String result = genreServiceImpl.updateById(tempGenreDto);

        assertEquals("Genre has been updated.", result);
    }

    @Test
    void updateByIdNotExistsTest() {
        when(genreRepository.findById(anyLong())).thenReturn(Optional.empty());

        GenreDto tempGenreDto = new GenreDto(1L, "Action");

        String result = genreServiceImpl.updateById(tempGenreDto);

        assertEquals("Genre does not exist.", result);
    }

    @Test
    void deleteByIdExistsTest() {
        when(genreRepository.findById(anyLong())).thenReturn(Optional.of(genreOne));

        String result = genreServiceImpl.deleteById(1L);

        verify(genreRepository, times(1)).deleteById(1L);
        assertEquals("Deleted genre id: 1" , result);
    }

    @Test
    void deleteByIdNotExistsTest() {
        when(genreRepository.findById(anyLong())).thenReturn(Optional.empty());

        String result = genreServiceImpl.deleteById(1L);

        verify(genreRepository, times(0)).deleteById(anyLong());
        assertEquals("Could not find genre with id: 1", result);
    }
}