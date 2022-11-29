package com.example.x3.MovieManagementApp.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "ratings")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Ratings implements Serializable {

    @EmbeddedId
    private RatingsPK ratingsPK;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "movie_id", insertable = false, updatable = false)
    private Movies movie;

    @Column(name = "review")
    private String review;

    @Column (name = "weight")
    private int weight;

    @Column (name = "rating")
    private int rating;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ratings)) return false;
        Ratings ratings = (Ratings) o;
        return ratingsPK.equals(ratings.ratingsPK);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ratingsPK);
    }
}
