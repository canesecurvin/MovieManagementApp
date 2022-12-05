package com.example.x3.MovieManagementApp.services.impl;

import com.example.x3.MovieManagementApp.dtos.MovieDtos.MovieAddDto;
import com.example.x3.MovieManagementApp.dtos.MovieDtos.MovieDto;
import com.example.x3.MovieManagementApp.dtos.MovieDtos.MovieGenreAddDto;
import com.example.x3.MovieManagementApp.entities.Genres;
import com.example.x3.MovieManagementApp.entities.Movies;
import com.example.x3.MovieManagementApp.repositories.GenreRepository;
import com.example.x3.MovieManagementApp.repositories.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MovieServiceImplTest {

    @InjectMocks
    private MovieServiceImpl movieServiceImpl;

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private GenreRepository genreRepository;

    private Movies movieOne;
    private Movies movieTwo;
    private  Movies movieThree;
    private Genres genre;
    private MovieDto tempMovieDto;
    private HashSet<Genres> genreSet = new HashSet<>();

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

    @Test
    void findAllByMovieNameTest() {
        when(movieRepository.findAllByMovieName(anyString())).thenReturn(tempMovies);

        List<Movies> foundList = movieServiceImpl.findAllByMovieName("Test Movie");

        assertEquals(3, foundList.size());
        assertNotNull(foundList);
    }

    @Test
    void findAllByReleaseYearTest() {
        when(movieRepository.findAllByReleaseYear(anyInt(), any(Sort.class)))
                .thenReturn(tempMovies);

        List<Movies> foundList = movieServiceImpl.findAllByReleaseYear(2000,
                Sort.by(Sort.Direction.ASC, "movieName"));

        assertEquals(3, foundList.size());
        assertNotNull(foundList);
    }

    @Test
    void findAllByGenresIsPresentTest() {
        when(genreRepository.findByGenre(anyString())).thenReturn(Optional.of(genre));
        when(movieRepository.findAllByGenres(any(Genres.class))).thenReturn(tempMovies);

        List<Movies> foundList = movieServiceImpl.findAllByGenres("Action");

        assertEquals(3, foundList.size());
        assertNotNull(foundList);
    }

    @Test
    void findAllByGenresIsEmptyTest() {
        when(genreRepository.findByGenre(anyString())).thenReturn(Optional.empty());

        List<Movies> foundList = movieServiceImpl.findAllByGenres("Action");

        assertEquals(0, foundList.size());
        assertTrue(foundList.isEmpty());
    }

    @Test
    void findByIdIsFoundTest() {
        when(movieRepository.findById(anyLong())).thenReturn(Optional.of(movieOne));

        Movies foundMovie = movieServiceImpl.findById(1L);

        assertNotNull(foundMovie);
        assertEquals(1L, foundMovie.getId());

    }

    @Test
    void findByIdNotFoundTest() {
        when(movieRepository.findById(anyLong())).thenReturn(Optional.empty());

        Movies foundMovie = movieServiceImpl.findById(1L);

        assertNull(foundMovie);
    }

    @Test
    void saveTest() {
        List<Movies> tempSaveList = new ArrayList<>();
        MovieAddDto movieOneAddDto = MovieAddDto.builder()
                .movieName("Test Movie")
                .releaseYear(2000)
                .movieLength(120)
                .rating(5)
                .build();

        when(movieRepository.findAllByMovieName(anyString())).thenReturn(tempSaveList);
        when(movieRepository.save(any(Movies.class))).thenReturn(movieOne);

        Optional<List<Movies>> tempList = movieServiceImpl.save(movieOneAddDto);

        verify(movieRepository, times(1)).save(any(Movies.class));
        assertEquals(Optional.empty(), tempList);
    }

    @Test
    void saveAlreadyExistsTest() {
        MovieAddDto movieOneAddDto = MovieAddDto.builder()
                .movieName("Test Movie")
                .releaseYear(2000)
                .movieLength(120)
                .rating(5)
                .build();

        when(movieRepository.findAllByMovieName(anyString())).thenReturn(tempMovies);

        Optional<List<Movies>> tempList = movieServiceImpl.save(movieOneAddDto);

        verify(movieRepository, times(0)).save(any(Movies.class));
        assertTrue(tempList.isPresent());
        assertEquals(1, tempList.get().size());
    }

    @Test
    void updateByIdFoundTest() {
        when(movieRepository.findById(anyLong())).thenReturn(Optional.of(movieOne));

        String result = movieServiceImpl.updateById(tempMovieDto);

        assertEquals("Movie updated.", result);
    }

    @Test
    void updateByIdNotFoundTest() {
        when(movieRepository.findById(anyLong())).thenReturn(Optional.empty());

        String result = movieServiceImpl.updateById(tempMovieDto);

        assertEquals("Movie not found.", result);
    }

    @Test
    void addGenreFoundToMovieFoundTest() {
        ArrayList<Long> genreIdList = new ArrayList<>();
        genreIdList.add(1L);
        MovieGenreAddDto tempMovieGenreAddDto = MovieGenreAddDto.builder()
                .movieId(1L)
                .genres(genreIdList)
                .build();

        when(genreRepository.findById(anyLong())).thenReturn(Optional.of(genre));
        when(movieRepository.findById(anyLong())).thenReturn(Optional.of(movieThree));

        String result = movieServiceImpl.addGenreToMovie(tempMovieGenreAddDto);

        assertEquals("Genre(s) have been added.", result);
    }

    @Test
    void addGenreFoundToMovieNotFoundTest() {
        ArrayList<Long> genreIdList = new ArrayList<>();
        genreIdList.add(1L);
        MovieGenreAddDto tempMovieGenreAddDto = MovieGenreAddDto.builder()
                .movieId(1L)
                .genres(genreIdList)
                .build();

        when(movieRepository.findById(anyLong())).thenReturn(Optional.empty());

        String result = movieServiceImpl.addGenreToMovie(tempMovieGenreAddDto);

        assertEquals("Cannot find movieId: 1", result);
    }

    @Test
    void addGenreNotFoundToMovieFoundTest() {
        ArrayList<Long> genreIdList = new ArrayList<>();
        genreIdList.add(1L);
        MovieGenreAddDto tempMovieGenreAddDto = MovieGenreAddDto.builder()
                .movieId(1L)
                .genres(genreIdList)
                .build();

        when(genreRepository.findById(anyLong())).thenReturn(Optional.empty());
        when(movieRepository.findById(anyLong())).thenReturn(Optional.of(movieThree));

        String result = movieServiceImpl.addGenreToMovie(tempMovieGenreAddDto);

        assertEquals("Attempted to add genreId: 1 that did not exist.", result);
    }

    @Test
    void deleteByIdFoundTest() {
        when(movieRepository.findById(anyLong())).thenReturn(Optional.of(movieOne));

        String result = movieServiceImpl.deleteById(1L);

        verify(movieRepository, times(1)).deleteById(1L);
        assertEquals("Deleted movie id: 1", result);
    }

    @Test
    void deleteByIdNotFoundTest() {
        when(movieRepository.findById(anyLong())).thenReturn(Optional.empty());

        String result = movieServiceImpl.deleteById(1L);

        verify(movieRepository, times(0)).deleteById(1L);
        assertEquals("Could not find movie with id: 1", result);
    }

    @Test
    void findAllTest() {
        when(movieRepository.findAll(any(Sort.class))).thenReturn(tempMovies);

        List<Movies> allMovieList = movieServiceImpl.findAll(Sort.by(Sort.Direction.ASC, "movieName"));

        assertEquals(3, allMovieList.size());
    }
}