package com.example.x3.MovieManagementApp.dtos.FavoritesDto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FavoritesAddDto {
    private Long userId;
    private Long movieId;
}
