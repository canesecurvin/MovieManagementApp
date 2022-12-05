package com.example.x3.MovieManagementApp.dtos.MovieDtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieDto {

    private long id;
    private String movieName;
    private int releaseYear;
    private int movieLength;
    private double rating;

}
