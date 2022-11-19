import React from "react";
import CommentLogJsx from "./CommentLog/CommentLog.jsx";
import './Movie.css'

function MovieJsx(props){
    return (
        <div class="movie-container">
            <div className="movie-info">
                <div className="movie-pic"></div>
                <div className="info">
                    <p><b>Rating: </b>{props.rating}</p>
                    <p>{props.movie_name}</p>
                    <p>{props.release_year}</p>
                    <p>{props.director}</p>
                    <p>{props.cast}</p>
                </div>
            </div>
            <h3>Comments</h3>
            <CommentLogJsx />
        </div>
    );
}

export default MovieJsx;