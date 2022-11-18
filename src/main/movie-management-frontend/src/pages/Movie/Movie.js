import React from "react";
import MovieJsx from "./Movie.jsx";

const movieInfo = {
    movie_name: 'Titanic',
    release_year: 1996,
    director: 'Paul Rudd',
    cast: 'Leonardo Dicaprio',
    rating: 5
}
function Movie(){
    return (
        <div>
            <MovieJsx {...movieInfo}/>
        </div>
    );
}

export default Movie;