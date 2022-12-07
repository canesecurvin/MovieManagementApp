package com.example.x3.MovieManagementApp.dtos.MovieDtos;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieGenreRemoveDto {

    private long movieId;
    private List<Long> genres;

}
