package com.example.x3.MovieManagementApp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "favorites")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Favorites implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "user_id")
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JsonBackReference
    private User user;

    @JoinColumn(name = "movie_id")
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JsonBackReference
    private Movies movie;

    public Favorites(User user, Movies movie){
        this.user = user;
        this.movie = movie;
    }
}
