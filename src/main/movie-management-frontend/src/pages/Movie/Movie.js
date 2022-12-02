import React from "react";
import MovieJsx from "./Movie.jsx";
import {useParams} from "react-router-dom";
import './Movie.css';

function Movie(){
    const id = useParams();
    return (
        <div className="container">
            <p>HEEYY</p>
            <MovieJsx className="movie-container" {...id}/>
        </div>
    );
}

export default Movie;