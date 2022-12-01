package com.example.x3.MovieManagementApp.dtos.MovieDtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieGenreAddDto {

    private long movieId;
    private List<Long> genres;

}
