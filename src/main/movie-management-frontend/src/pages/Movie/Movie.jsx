import React, {useEffect, useState} from "react";
import CommentLogJsx from "./CommentLog/CommentLog.jsx";
import MovieService from "../../services/MovieService.js";
import AddCommentJsx from "./CommentLog/AddComment.jsx";
import './Movie.css'

function MovieJsx(props){
    const [values, setValues] = useState({
        movie: {}
    })
    useEffect(()=> {
        MovieService.getMovie(props.id).then(res => {
            setValues(()=> ({
                movie: res.data
            }));
        }).catch(err => {
            alert(err);
        })
    }, [])
    return (
        <>
            <h1><b>{values.movie.movieName}</b></h1>
            <div className="movie-info">
                <div className="movie-pic"></div>
                <div className="info">
                    <p><b>Rating: </b>{values.movie.rating}</p>
                    <p><b>Released: </b>{values.movie.releaseYear}</p>
                    <p><b>Duration: </b>{values.movie.movieLength} Minutes</p>
                </div>
            </div>
            <AddCommentJsx movieId={props.id}/>
            <h3>Comments</h3>
            <CommentLogJsx id={props.id}/>
        </>
    );
}

export default MovieJsx;