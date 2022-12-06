//package com.example.x3.MovieManagementApp.entities;
//
//import javax.persistence.Column;
//import javax.persistence.Embeddable;
//import java.io.Serializable;
//import java.util.Objects;
//
//@Embeddable
//public class UserMovieFavoritesPK implements Serializable {
//
//    //Commented totally for now but we might be will need it for future expansion
//
//    private static final long serialVersionUID = -456431918721734308L;
//    @Column(name = "user_id")
//    private long userId;
//
//    @Column(name = "movie_id")
//    private long movieId;
//
//    public UserMovieFavoritesPK() {
//    }
//
//    public UserMovieFavoritesPK(long userId, long movieId) {
//        this.userId = userId;
//        this.movieId = movieId;
//    }
//
//    public long getUserId() {
//        return userId;
//    }
//
//    public long getMovieId() {
//        return movieId;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof UserMovieFavoritesPK)) return false;
//        UserMovieFavoritesPK userMovieFavoritesPK = (UserMovieFavoritesPK) o;
//        return userId == userMovieFavoritesPK.userId && movieId == userMovieFavoritesPK.movieId;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(userId, movieId);
//    }
//}
