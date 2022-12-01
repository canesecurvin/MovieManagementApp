package com.example.x3.MovieManagementApp.services.impl;

import com.example.x3.MovieManagementApp.dtos.MovieDtos.MovieAddDto;
import com.example.x3.MovieManagementApp.dtos.MovieDtos.MovieDto;
import com.example.x3.MovieManagementApp.dtos.MovieDtos.MovieGenreAddDto;
import com.example.x3.MovieManagementApp.entities.Genres;
import com.example.x3.MovieManagementApp.entities.Movies;
import com.example.x3.MovieManagementApp.repositories.GenreRepository;
import com.example.x3.MovieManagementApp.repositories.MovieRepository;
import com.example.x3.MovieManagementApp.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository, GenreRepository genreRepository) {
        movieRepository = movieRepository;
        genreRepository = genreRepository;
    }

    @Override
    public List<Movies> findAllByMovieName(String name) {
        return movieRepository.findAllByMovieName(name);
    }

    @Override
    public List<Movies> findAllByReleaseYear(int year, Sort sort) {
        return movieRepository.findAllByReleaseYear(year, sort);
    }

    @Override
    public List<Movies> findAllByGenres(String genre) {
        return movieRepository.findAllByGenres(genre);
    }

    @Override
    public Movies findById(long id) {
        Optional<Movies> tempMovie = movieRepository.findById(id);

        return tempMovie.orElse(null);

    }

    @Override
    public Optional<List<Movies>> save(MovieAddDto movieAddDto) {
        ArrayList<Movies> tempMovieList = (ArrayList<Movies>) movieRepository.findAllByMovieName(movieAddDto.getMovieName());
        ArrayList<Movies> returnList = new ArrayList<>();

            for (Movies movie : tempMovieList) {
                if (movie.getMovieName().equalsIgnoreCase(movieAddDto.getMovieName())
                        && movie.getReleaseYear() == movieAddDto.getReleaseYear()) {
                    returnList.add(movie);
                }
            }
            if (!tempMovieList.isEmpty()) return Optional.of(returnList);

        Movies newMovie = new Movies();
        newMovie.setMovieName(movieAddDto.getMovieName());
        newMovie.setMovieLength(movieAddDto.getMovieLength());
        newMovie.setReleaseYear(movieAddDto.getReleaseYear());
        movieRepository.save(newMovie);

        return Optional.empty();
    }

    @Override
    public String updateById(MovieDto movieDto) {
        Optional<Movies> tempMovie = movieRepository.findById(movieDto.getId());

        if (tempMovie.isEmpty()) {
            return "Movie not found.";
        }

        tempMovie.ifPresent(movies -> {
            movies.setMovieName(movieDto.getMovieName());
            movies.setReleaseYear(movieDto.getReleaseYear());
            movies.setMovieLength(movieDto.getMovieLength());
            movies.setRating(movieDto.getRating());
            movieRepository.saveAndFlush(movies);
        });
        return "Movie updated.";
    }

    @Override
    public String addGenreToMovie(MovieGenreAddDto movieGenreAddDto) {
        if (movieGenreAddDto.getGenres().isEmpty()) return "No genres have been added.";

        Optional<Movies> tempMovie = movieRepository.findById(movieGenreAddDto.getMovieId());

        if (tempMovie.isEmpty()) return "Cannot find movieId: " + movieGenreAddDto.getMovieId();
        Movies updateMovie = tempMovie.get();

        for (long genreId : movieGenreAddDto.getGenres()) {
            if (genreRepository.findById(genreId).isPresent()) {
                updateMovie.addGenre(genreRepository.findById(genreId).get());
            }
        }

        movieRepository.save(updateMovie);

        return "Genre(s) have been added.";
    }

    @Override
    public String deleteById(long id) {
        Optional<Movies> tempMovie = movieRepository.findById(id);

        if (tempMovie.isPresent()) {
            movieRepository.deleteById(id);
            return "Deleted movie id: " + id;
        }
        return "Could not find movie with id: " + id;

    }

    @Override
    public List<Movies> findAll(Sort sort) {
        return movieRepository.findAll(sort);
    }

}
