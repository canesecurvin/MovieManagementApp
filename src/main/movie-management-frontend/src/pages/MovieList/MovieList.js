import React from "react";
import MovieListJsx from "./MovieList.jsx";
import './MovieList.css';
import NavigationJsx from "../../components/Navigation.jsx";

function MovieList(){
    return (
        <div class="containers">
            <NavigationJsx />
            <div className="mov-container">
                <h1>Movies List</h1>
                <div class="movie-list-container">
                    <MovieListJsx/>
                </div>
            </div>
        </div>
    );
}

export default MovieList;