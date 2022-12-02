import React, {useEffect, useState} from "react";
import CommentLogJsx from "./CommentLog/CommentLog.jsx";
import MovieService from "../../services/MovieService.js";
import AuthService from "../../services/AuthService";
import MovieRatingsService from "../../services/MovieRatingsService.js";
import RatingModalJsx from "./RatingModal.jsx";
import RatingStarsJsx from '../../components/RatingStars.jsx'
import Button from 'react-bootstrap/Button';
import './Movie.css'

function MovieJsx(props){
    const currentUser = AuthService.getCurrentUser();
    const [values, setValues] = useState({
        movie: {}
    })
    const [rating, setRating] = useState(values.movie.rating);

    const [show, setShow] = useState(false);
    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);
    useEffect(()=> {
        try{
            MovieService.getMovie(props.id).then(res => {
                setValues(()=> ({
                    movie: res.data
                }));
            }).catch(err => {
                alert(err);
            })
        }catch(e){console.log(e)}
        try {
            MovieRatingsService.getMovieRatingByUserAndMovie(currentUser.id, props.id).then(res => {
                console.log('---mov', res.data)
                setRating(res.data.rating)
            }).catch(err => {
                console.log(err);
            })
        }catch(e){console.log(e)}
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
            <h3>Comments</h3>
            <CommentLogJsx id={props.id}/>
        </>
    );
}

export default MovieJsx;