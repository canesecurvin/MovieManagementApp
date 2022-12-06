package com.example.x3.MovieManagementApp.repositories;

import com.example.x3.MovieManagementApp.entities.Genres;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GenreRepositoryTests {

    @Autowired
    GenreRepository genreRepository;


    @Test
    @Order(1)
    @Rollback(value = false)
    void saveTest() {
        Genres genresOne = Genres.builder()
                .id(1L)
                .genre("Action")
                .build();

        Genres tempGenre = genreRepository.save(genresOne);

        assertThat(tempGenre.getId()).isEqualTo(1L);
        assertThat(tempGenre.getGenre()).isEqualTo("Action");
    }

    @Test
    @Order(2)
    void findByIdTest() {
        Optional<Genres> tempGenre = genreRepository.findById(1L);

        assertTrue(tempGenre.isPresent());
        assertThat(tempGenre.get().getId()).isEqualTo(1L);
    }

    @Test
    @Order(3)
    void findByGenreTest() {
        Optional<Genres> tempGenre = genreRepository.findByGenre("Action");

        assertTrue(tempGenre.isPresent());
        assertThat(tempGenre.get().getGenre()).isEqualTo("Action");
    }

    @Test
    @Order(4)
    void findAllTest() {
        Genres genreTwo = Genres.builder()
                .id(2L)
                .genre("Fantasy")
                .build();

        genreRepository.save(genreTwo);

        List<Genres> tempList = genreRepository.findAll();

        assertThat(tempList.size()).isEqualTo(2);
        assertThat(tempList.get(0).getId()).isEqualTo(1L);
        assertThat(tempList.get(1).getId()).isEqualTo(2L);
    }

    @Test
    @Order(5)
    void updateByIdTest() {
        Optional<Genres> tempGenre = genreRepository.findById(1L);

        tempGenre.get().setGenre("Action!");

        genreRepository.save(tempGenre.get());

        Optional<Genres> updatedGenre = genreRepository.findById(1L);

        assertThat(updatedGenre.get().getGenre()).isEqualTo("Action!");
    }

    @Test
    @Order(6)
    @Rollback(value = false)
    void deleteByIdTest() {
        genreRepository.deleteById(1L);

        Optional<Genres> tempGenre = genreRepository.findById(1L);

        assertTrue(tempGenre.isEmpty());

    }
}
