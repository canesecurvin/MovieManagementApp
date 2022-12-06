//package com.example.x3.MovieManagementApp.services.impl;
//
//import com.example.x3.MovieManagementApp.dtos.UserMovieFavoritesDto.UserMovieFavoritesAddDto;
//import com.example.x3.MovieManagementApp.entities.*;
//import com.example.x3.MovieManagementApp.repositories.MovieRepository;
//import com.example.x3.MovieManagementApp.repositories.UserMovieFavoritesRepository;
//import com.example.x3.MovieManagementApp.repositories.UserRepository;
//import com.example.x3.MovieManagementApp.services.UserMovieFavoritesService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.*;
//
//@Service
//public class UserMovieFavoritesServiceImpl implements UserMovieFavoritesService {
//    //Commented totally for now but we might be will need it for future expansion
//
//    @Autowired
//    UserMovieFavoritesRepository userMovieFavoritesRepository;
//
//    @Autowired
//    MovieRepository movieRepository;
//
//    @Autowired
//    UserRepository userRepository;
//
//
//    @Override
//    public List<UserMovieFavorites> findAll() {
//        return userMovieFavoritesRepository.findAll();
//    }
//
//    @Override
//    @Transactional
//    public UserMovieFavorites findByUserMovieFavoritesPK(long userId, long movieId) {
//
//        UserMovieFavoritesPK userMovieFavoritesPK = new UserMovieFavoritesPK(userId, movieId);
//
//        return userMovieFavoritesRepository.findById(userMovieFavoritesPK).orElse(null);
//    }
//
//    @Override
//    public List<Movies> findAllFavoritesForUser(long userId) {
//
//        List<UserMovieFavorites> tempListUserMovieFavorites =
//                userMovieFavoritesRepository.findAllByUserId(userId);
//
//        if (tempListUserMovieFavorites.isEmpty()) {
//            return Collections.emptyList();
//        }
//            List<Movies> allMovies= new ArrayList<>();
//            for (UserMovieFavorites x : tempListUserMovieFavorites) {
//
//                allMovies.add(x.getMovie());
//            }
//            return allMovies;
//    }
//
//    @Override
//    public List<User> findAllUsersWithFavorites(long movieId) {
//
//        List<UserMovieFavorites> tempListUserMovieFavorites =
//                userMovieFavoritesRepository.findAllByMovieId(movieId);
//
//        if (tempListUserMovieFavorites.isEmpty()) {
//            return Collections.emptyList();
//        }
//        List<User> allUsers= new ArrayList<>();
//        for (UserMovieFavorites x : tempListUserMovieFavorites) {
//
//            allUsers.add(x.getUser());
//        }
//        return allUsers;
//    }
//
//    @Override
//    @Transactional
//    public String updateFavorite(UserMovieFavoritesAddDto userMovieFavoritesAddDto) {
//        long userId = userMovieFavoritesAddDto.getUserId();
//        long movieId = userMovieFavoritesAddDto.getMovieId();
//
//        Optional<String> userMovieCheck = validateUserAndMovie(userId, movieId);
//        if (userMovieCheck.isPresent()) {
//            return userMovieCheck.get();
//        }
//
//        UserMovieFavorites userMovieFavorites;
//
//        Optional<UserMovieFavorites> tempUserMovieFavorite = userMovieFavoritesRepository.findByUserIdAndMovieId(userId, movieId);
//        if (tempUserMovieFavorite.isEmpty()) {
//            userMovieFavorites = new UserMovieFavorites();
//            var tempUserMoviePK = new UserMovieFavoritesPK(userId, movieId);
//
//            userMovieFavorites.setUserMoviePK(tempUserMoviePK);
//        }
//        else {
//            userMovieFavorites = tempUserMovieFavorite.get();
//        }
////        userMovieFavorites.setFavorite(userMovieFavoritesAddDto.isFavorite());
//        userMovieFavoritesRepository.save(userMovieFavorites);
//
//        return "Favorite status has been updated.";
//    }
//
//    private Optional<String> validateUserAndMovie(long userId, long movieId) {
//        Optional<User> tempUser = userRepository.findById(userId);
//        Optional<Movies> tempMovie = movieRepository.findById(movieId);
//
//        if (tempUser.isEmpty() || tempMovie.isEmpty()) {
//            StringBuilder tempString = new StringBuilder();
//            if (tempUser.isEmpty()) tempString.append("Cannot find userId: ")
//                    .append(userId)
//                    .append("\n");
//
//            if (tempMovie.isEmpty()) tempString.append("Cannot find movieId: ")
//                    .append(movieId);
//
//            return Optional.of(tempString.toString());
//        }
//
//        return Optional.empty();
//    }
//}
