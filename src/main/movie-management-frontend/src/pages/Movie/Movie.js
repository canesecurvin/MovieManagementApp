import React from "react";
import MovieJsx from "./Movie.jsx";
import {useParams} from "react-router-dom";
import NavigationJsx from '../../components/Navigation';
import './Movie.css';

function Movie(){
    const id = useParams();
    return (
        <div className="containers">
            <NavigationJsx />
            <div className="mov-container">
                <MovieJsx className="movie-container" {...id}/>
            </div>
        </div>
    );
}

export default Movie;