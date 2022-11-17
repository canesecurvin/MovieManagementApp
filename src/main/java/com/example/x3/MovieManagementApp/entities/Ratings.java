package com.example.x3.MovieManagementApp.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Ratings")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ratings {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_email")
    private User user;

    @Column(name = "review")
    private String review;

    @Column (name = "weight")
    private int weight;
}
