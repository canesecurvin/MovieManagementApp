package com.example.x3.MovieManagementApp.repositories;

import com.example.x3.MovieManagementApp.entities.Movies;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepositories extends JpaRepository<Movies, Long> {

    List<Movies> findAllByMovieName(String name);

    List<Movies> findAllByReleaseYear(int year, Sort sort);

}
