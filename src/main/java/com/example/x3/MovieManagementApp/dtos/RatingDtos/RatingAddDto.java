package com.example.x3.MovieManagementApp.dtos.RatingDtos;

import com.example.x3.MovieManagementApp.entities.RatingsPK;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RatingAddDto {

    private RatingsPK ratingsPK;
    private int rating;
    private String review;
}
