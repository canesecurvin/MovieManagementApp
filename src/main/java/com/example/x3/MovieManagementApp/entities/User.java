package com.example.x3.MovieManagementApp.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements Serializable {

    @Id
    @Column(unique = true, nullable = false, name = "email", length = 80)
    private String email;

    @Column (nullable = false, name = "password", length = 200)
    private String password;

    @Column (unique = true, nullable = false, name = "display_name", length = 15)
    private String displayName;

    @Column (name = "first_name", length = 20)
    private String firstName;

    @Column (name = "last_name", length = 20)
    private String lastName;

    @OneToMany(mappedBy = "user")
    private List<MovieComments> comments;

    @OneToMany(mappedBy = "user")
    private List<Ratings> ratings;

//    @ManyToMany
//    @JoinTable(
//            name = "user_movie_favorites",
//            joinColumns = @JoinColumn(name = "user_email", referencedColumnName = "email"),
//            inverseJoinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id")
//    )
//    private List<Movies> favoriteMovies;
}
