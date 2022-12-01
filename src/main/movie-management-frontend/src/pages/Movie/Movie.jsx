import React, {useEffect, useState} from "react";
import CommentLogJsx from "./CommentLog/CommentLog.jsx";
import MovieService from "../../services/MovieService.js";
import MovieRatingsService from "../../services/MovieRatingsService.js";
import AddCommentJsx from "./CommentLog/AddComment.jsx";
import RatingModalJsx from "./RatingModal.jsx";
import RatingStarsJsx from '../../components/RatingStars.jsx'
import Button from 'react-bootstrap/Button';
import './Movie.css'

function MovieJsx(props){
    const [values, setValues] = useState({
        movie: {}
    })
    const [rating, setRating] = useState(values.movie.rating);

    const [show, setShow] = useState(false);
    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);
    useEffect(()=> {
        MovieService.getMovie(props.id).then(res => {
            setValues(()=> ({
                movie: res.data
            }));
        }).catch(err => {
            alert(err);
        })
        MovieRatingsService.getMovieRatingByUser(37, props.id).then(res => {
            setRating(res.data.rating)
        }).catch(err => {
            alert('no rating');
        })
    },[])
    const renderStars = () => {
        if (rating !== undefined) {
            return (
                <>
                  <RatingStarsJsx rating={rating}/>  
                </>
            )
        }else {
            return (
                <>
                </>
            )
        }
    }

    return (
        <>
            <h1><b>{values.movie.movieName}</b></h1>
            <div className="movie-info">
                <div className="movie-pic"></div>
                <div className="info">
                    {renderStars()}
                    <p><b>Released: </b>{values.movie.releaseYear}</p>
                    <p><b>Duration: </b>{values.movie.movieLength} Minutes</p>
                    <Button variant="primary" onClick={handleShow}>Rate</Button>
                    <RatingModalJsx movieId={props.id} userRating={rating} show={show} handleClose={handleClose}/>
                </div>
            </div>
            <AddCommentJsx movieId={props.id}/>
            <h3>Comments</h3>
            <CommentLogJsx id={props.id}/>
        </>
    );
}

export default MovieJsx;