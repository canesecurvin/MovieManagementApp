import React, {useEffect, useState} from "react";
import {useLocation} from 'react-router-dom';
import CommentLogJsx from "./CommentLog/CommentLog.jsx";
import MovieService from "../../services/MovieService.js";
import AuthService from "../../services/AuthService";
import FavoritesService from "../../services/FavoritesService.js";
import MovieRatingsService from "../../services/MovieRatingsService.js";
import RatingModalJsx from "./RatingModal.jsx";
import RatingStarsJsx from '../../components/RatingStars.jsx';
import Button from 'react-bootstrap/Button';
import { HiHandThumbUp, HiOutlineHandThumbUp } from "react-icons/hi2";
import './Movie.css'

function MovieJsx(props){
    const location = useLocation();
    const currentUser = AuthService.getCurrentUser();
    const [ratingSaved, setRatingSaved] = useState(false);
    const [newRatingSaved, setNewRatingSaved] = useState(false);
    const [newRating, setNewRating] = useState(0);
    const [values, setValues] = useState({
        movie: {}
    })
    const [rating, setRating] = useState(values.movie.rating);
    const [favorited, setFavorited] = useState(false);
    const [loading, setLoading] = useState(true);
    const [show, setShow] = useState(false);
    const handleClose = () => {
        document.getElementById("rating-button").style.display = 'none';
        setRatingSaved(true)
        setTimeout(() => {
            setRatingSaved(false);
          }, "3000")
        setShow(false)
    };
    const handleShow = () => setShow(true);
    useEffect(()=> {
        let img = document.getElementById(`poster`);
        let url = location.state.image.substring(3, location.state.image.length)
        img.style.backgroundImage = `url(${url})`;
        try{
            MovieService.getMovie(props.id).then(res => {
                setValues(()=> ({
                    movie: res.data
                }));
            }).catch(error => {
                console.log(error);
            })
        }catch(e){console.log(e)}
        try {
            MovieRatingsService.getMovieRatingByUserAndMovie(currentUser.id, props.id).then(res => {
                FavoritesService.getFavoriteByUserIdAndMovieId(currentUser.id, Number(props.id)).then(res => {
                    setLoading(false);
                    setRating(res.data.rating)
                    setFavorited(res.data);
                }).catch(error => {console.log(error);})
            }).catch(err => {
                console.log(err);
            })
        }catch(e){console.log(e)}
        try {
            FavoritesService.getFavoriteByUserIdAndMovieId(currentUser.id, Number(props.id)).then(res => {
                setFavorited(res.data);
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
    const renderGenres = () => {
        if (values.movie.genres!=undefined) {
            return (
                <>
                    {values.movie.genres.map(function(genre, i){
                        return (
                        <><span>
                            {genre.genre}
                            {i!=values.movie.genres.length-1?(<>, </>):(<></>)}
                            </span></>)
                    })}
                </>
            )
        }else return (<></>)
    }
    function addToFavorites(movieId){
        FavoritesService.addUserFavorites(currentUser.id, movieId).then(res=>{
            setFavorited(true);
        })
    }
    function removeFromFavorites(movieId){
        FavoritesService.deleteFavoriteByMovieIdAndUserId(movieId, currentUser.id).then(res=> {
            setFavorited(false);
        })
    }
    return (
        <>
            <h1><b>{values.movie.movieName}</b></h1>
            <div className="movie-info">
                <div className="poster" id="poster"></div>
                {loading?(<></>):(<div className="info">
                    {renderStars()}
                    {newRatingSaved ? (<><RatingStarsJsx rating={newRating}/></>) : (<></>)}
                    <p><b>Released: </b>{values.movie.releaseYear}</p>
                    <p><b>Duration: </b>{values.movie.movieLength} Minutes</p>
                    {values.movie.genres!=undefined ? 
                    (   <>
                            <p><b>Genres: </b>{renderGenres()}</p>
                        </>
                    ):(<></>)}
                    <div className="favorite">
                        <div className='thumbs-up'>
                            {favorited?(<button type="button" className="like" onClick={()=>{removeFromFavorites(props.id)}}><HiHandThumbUp className="like-icon"/></button>):(<button type="button" className="like" onClick={()=>{addToFavorites(props.id)}}><HiOutlineHandThumbUp className="like-icon"/></button>)}
                        </div>
                    </div>
                    {!rating ? (<Button variant="primary" id="rating-button" onClick={handleShow}>Rate</Button>):(<></>)}
                    {ratingSaved ? (<div className="success-message"><p>Rating Saved</p></div>) : (<></>)}
                    <RatingModalJsx movieId={props.id} userRating={rating} show={show} handleClose={handleClose} setNewRating={setNewRating} setNewRatingSaved={setNewRatingSaved}/>
                </div>)}
            </div>
            <h3>Comments</h3>
            <CommentLogJsx id={props.id}/>
        </>
    );
}

export default MovieJsx;