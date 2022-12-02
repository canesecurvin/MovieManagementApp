import React, {useEffect, useState} from "react";
import './Tabs.css';
import MovieRatingsService from "../../../services/MovieRatingsService";
import MovieService from "../../../services/MovieService";
import MovieCommentsService from "../../../services/MovieCommentsService";
import RatingStarsJsx from "../../../components/RatingStars";
import Card from 'react-bootstrap/Card';
import Button from "react-bootstrap/Button";
import { RiSubtractLine } from "react-icons/ri";
import {useNavigate} from "react-router-dom";
import AuthService from "../../../services/AuthService";

function RecentActivityJsx(){
    const currentUser = AuthService.getCurrentUser();
    const navigate = useNavigate();
    function showSingleMovie(id){
        navigate(`/movie/${id}`);
    }
    const [movieActivity, setMovieActivity] = useState({
        activity: []
    });
    function getActivity(){
        let reviews = [];
        MovieRatingsService.getMovieRatingByUser(currentUser.id).then(res => {
            res.data.forEach(mov => {
                let curReview = {};
                MovieService.getMovie(mov.ratingsPK.movieId).then(res => {
                    MovieCommentsService.getMovieCommentByMovieAndUser(mov.ratingsPK.movieId,currentUser.id).then(res2 => {
                        curReview.id = mov.ratingsPK.movieId;
                        curReview.rating = mov.rating;
                        curReview.review = mov.review;
                        curReview.movieName = res.data.movieName;
                        let comList = []
                        res2.data.forEach(com => {
                            comList.push(com.comment);
                        })
                        curReview.comments = comList;
                        reviews.push(curReview);
                        setMovieActivity((movieActivity) => ({
                            ...movieActivity,
                            activity: [...reviews]
                        }));
                    }).catch(error=> {console.log(error);})
                }).catch(error => {console.log(error);})
            })
        }).catch(error => {console.log(error);})
    }
    useEffect(()=>{
        getActivity();
    },[])
    const activityMap = movieActivity.activity.map( function(act){
        const comms = act.comments.map(function(comment){
            return (
                <>
                    <p className="com"><RiSubtractLine/> {comment}</p>
                </>
            );
        })
        return (
            <>
                <div className="activity">
                    <Card>
                        <Card.Header><b>{act.movieName}</b></Card.Header>
                        <Card.Body>
                        {/* <p><b>Rating:</b> {act.rating}</p> */}
                        <RatingStarsJsx className="stars" rating={act.rating} />
                        <p className="review"><b>Review:</b> {act.review}</p>
                        <p><b>Comments:</b></p>
                        {comms}
                        </Card.Body>
                        <Card.Footer>
                            <Button className="button" variant="primary" onClick={() => { showSingleMovie(act.id)}}>View Movie</Button>
                        </Card.Footer>
                    </Card>
                </div>
            </>
        );
    });
    return (
        <div className="container">
            {activityMap}
        </div>
    );
}

export default RecentActivityJsx;