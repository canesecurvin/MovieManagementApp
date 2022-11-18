import React from "react";
import MovieListJsx from "./MovieList.jsx";
import './MovieList.css';

function MovieList(){
    return (
        <div class="container">
            <h1>Movies List</h1>
            <div class="movie-container">
                <MovieListJsx/>
            </div>
        </div>
    );
}

export default MovieList;