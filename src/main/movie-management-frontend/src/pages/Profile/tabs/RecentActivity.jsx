import React, {useEffect, useState} from "react";
import './Tabs.css';
import MovieRatingsService from "../../../services/MovieRatingsService";
import MovieService from "../../../services/MovieService";
import MovieCommentsService from "../../../services/MovieCommentsService";
import Card from 'react-bootstrap/Card';
import Button from "react-bootstrap/Button";
import AuthService from "../../../services/AuthService";

function RecentActivityJsx(){
    const [movieActivity, setMovieActivity] = useState({
        activity: []
    });
    // let activityMap = [];
    // const currentUser = JSON.parse(AuthService.getCurrentUser());
    function getActivity(){
        let reviews = [];
        MovieRatingsService.getMovieRatingByUser(39).then(res => {
            res.data.forEach(mov => {
                let curReview = {};
                // curReview.rating = mov.rating;
                // curReview.review = mov.review;
                MovieService.getMovie(mov.ratingsPK.movieId).then(res => {
                    console.log('getid', res.data)
                    // curReview.movieName = res.data.movieName;
                    MovieCommentsService.getMovieCommentByMovieAndUser(mov.ratingsPK.movieId,39).then(res2 => {
                        curReview.rating = mov.rating;
                        curReview.review = mov.review;
                        curReview.movieName = res.data.movieName;
                        console.log('com', res2.data)
                        let comList = []
                        res2.data.forEach(com => {
                            comList.push(com.comment);
                        })
                        curReview.comments = comList;
                        reviews.push(curReview);
                        console.log(curReview);
                        console.log(reviews);
                        setMovieActivity((movieActivity) => ({
                            ...movieActivity,
                            activity: [...reviews]
                        }));
                        console.log(movieActivity);
                    })
                })
            })
        })
    }
    useEffect(()=>{
        getActivity();
    },[])
    const activityMap = movieActivity.activity.map( function(act){
        console.log('----------IN AM-------------')
        const comms = act.comments.map(function(comment){
            return (
                <>
                    <p>{comment}</p>
                </>
            );
        })
        return (
            <>
                <div className="activity">
                    <Card>
                        <Card.Header><b>{act.movieName}</b></Card.Header>
                        <Card.Body>
                        <p><b>Rating:</b> {act.rating}</p>
                        <p><b>Review:</b> {act.review}</p>
                        <p><b>Comments:</b></p>
                        {comms}
                        </Card.Body>
                        <Card.Footer>
                            <Button className="button" variant="primary">View Movie</Button>
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