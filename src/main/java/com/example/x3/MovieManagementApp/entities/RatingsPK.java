package com.example.x3.MovieManagementApp.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RatingsPK implements Serializable {

    private static final long serialVersionUID = -576431918721734307L;
    @Column(name = "user_id")
    private long userId;

    @Column(name = "movie_id")
    private long movieId;

    public RatingsPK() {

    }

    public RatingsPK(long userId, long movieId) {
        this.userId = userId;
        this.movieId = movieId;
    }

    public long getUserId() {
        return userId;
    }

    public long getMovieId() {
        return movieId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RatingsPK)) return false;
        RatingsPK ratingsPK = (RatingsPK) o;
        return userId == ratingsPK.userId && movieId == ratingsPK.movieId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, movieId);
    }
}
