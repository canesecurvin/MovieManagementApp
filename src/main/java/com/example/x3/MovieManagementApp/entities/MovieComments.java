package com.example.x3.MovieManagementApp.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "movie_comments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieComments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "comment")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "user_email")
    private User user;






}
