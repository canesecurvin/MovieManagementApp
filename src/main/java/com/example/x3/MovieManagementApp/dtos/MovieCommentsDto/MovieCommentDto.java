package com.example.x3.MovieManagementApp.dtos.MovieCommentsDto;

import com.example.x3.MovieManagementApp.entities.Movies;
import com.example.x3.MovieManagementApp.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieCommentDto {

    private Long id;
    private String comment;
    private LocalDateTime timestamp;
    private Movies movie;
    private User user;
}
