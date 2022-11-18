package com.example.x3.MovieManagementApp.dtos.MovieDtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieDto {

    private long id;
    private String movieName;
    private int releaseYear;
    private int movieLength;
    private double rating;

}
