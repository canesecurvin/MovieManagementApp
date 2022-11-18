package com.example.x3.MovieManagementApp.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table(name = "genres")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Genres {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, name = "genre", length = 20)
    private String genre;

    @ManyToMany(mappedBy = "genres", fetch = FetchType.LAZY)
    private Set<Movies> movies = new HashSet<>();
}
