package com.example.x3.MovieManagementApp.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, name = "email", length = 80)
    private String email;

    @Column(nullable = false, name = "password", length = 200)
    private String password;

    @Column(unique = true, nullable = false, name = "display_name", length = 15)
    private String displayName;

    @Column(name = "first_name", length = 20)
    private String firstName;

    @Column(name = "last_name", length = 20)
    private String lastName;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonManagedReference
    @ToString.Exclude
    private Set<MovieComments> movieComments = new HashSet<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonManagedReference
    @ToString.Exclude
    private Set<Ratings> ratings = new HashSet<>();


    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "user_movie_favorites",
            joinColumns = {
                    @JoinColumn(name = "movie_id", referencedColumnName = "id", nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, updatable = false)}
    )
    @JsonManagedReference
    @ToString.Exclude
    private Set<Movies> movieFavorites = new HashSet<>();

    @ManyToOne
    @JsonManagedReference
    @ToString.Exclude
    private Set<UserMovieFavorites> userMovieFavorites = new HashSet<>();


    public void addFavorite(Movies movieFav) {  // user.addFavorite
        if (movieFavorites == null) {
            movieFavorites = new HashSet<>();
        }
        movieFavorites.add(movieFav);
    }


}
