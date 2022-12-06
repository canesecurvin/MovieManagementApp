//package com.example.x3.MovieManagementApp.entities;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import lombok.*;
//
//import javax.persistence.*;
//import java.io.Serializable;
//import java.util.Objects;
//
//@Entity
//@Table(name = "user_movie_favorites")
//@Getter
//@Setter
//@ToString
//@AllArgsConstructor
//@NoArgsConstructor
//public class UserMovieFavorites implements Serializable {
//
//    //Commented totally for now but we might be will need it for future expansion
//
//    @EmbeddedId
//    private UserMovieFavoritesPK userMoviePK;
//
////    @Column(name = "favorite")
////    private boolean favorite;
//
//    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
//    @JoinColumn(name = "movie_id", insertable = false, updatable = false)
//    @JsonBackReference
//    private Movies movie;
//
//    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
//    @JoinColumn(name = "user_id", insertable = false, updatable = false)
//    @JsonBackReference
//    private User user;
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof UserMovieFavorites)) return false;
//        UserMovieFavorites userMovieFavorites = (UserMovieFavorites) o;
//        return userMoviePK.equals(userMovieFavorites.userMoviePK);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(userMoviePK);
//    }
//}
