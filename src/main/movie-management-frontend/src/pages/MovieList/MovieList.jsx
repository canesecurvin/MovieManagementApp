import React from "react";
import MovieCardJsx from "./MovieCard/MovieCard.jsx";
import './MovieList.css';

const movies = [
    {movieName: 'Titanic', year: '1993', director: 'Paul Rudd', cast: 'Leanardo Dicaprio'},
    {movieName: 'Titanic', year: '1993', director: 'Paul Rudd', cast: 'Leanardo Dicaprio'}
]

function MovieListJsx(){
    return (
        <>
            {movies.map(function(mov, i){
                return (
                    <div key={i} className="movie-card">
                        <MovieCardJsx key={i} {...mov}/>
                    </div>
                );
            })}
        </>
    );
}

export default MovieListJsx;