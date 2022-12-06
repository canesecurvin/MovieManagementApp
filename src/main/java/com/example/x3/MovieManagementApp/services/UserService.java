package com.example.x3.MovieManagementApp.services;

import com.example.x3.MovieManagementApp.dtos.UserDtos.*;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<?> createNewUser(UserSignUpDto userSignUpDto);

    ResponseEntity<?> loginUser(UserLoginDto userLoginDto);

    ResponseEntity<?> updateBasicInfo(UserBasicUpdateRequestDto userBasicUpdateRequestDto, Long id);

    ResponseEntity<?> updateUserPassword(Long id, UpdateUserPasswordDto updateUserPasswordDto);

    String addFavoriteMovieToUser(UserMovieFavoritesAddDto userMovieFavoritesAddDto);

    String deleteMovieFromUserFavorites(UserMovieFavoritesRemoveDto userMovieFavoritesRemoveDto);
}
