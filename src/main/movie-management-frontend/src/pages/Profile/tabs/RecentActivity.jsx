import React, {useEffect, useState} from "react";
import MovieRatingsService from "../../../services/MovieRatingsService";
import MovieService from "../../../services/MovieService";
import MovieCommentsService from "../../../services/MovieCommentsService";
import ActivityCardJsx from "./ActivityCard";
import { RiSubtractLine } from "react-icons/ri";
import AuthService from "../../../services/AuthService";
import { AiOutlineLoading3Quarters } from "react-icons/ai";
import './Tabs.css';

function RecentActivityJsx(){
    const currentUser = AuthService.getCurrentUser();
    const [movieActivity, setMovieActivity] = useState({
        activity: []
    });
    const [loading, setLoading] = useState(true);
    function getActivity(){
        let reviews = [];
        MovieRatingsService.getMovieRatingByUser(currentUser.id).then(res => {
            if (res.data.length==0){
                setLoading(false);
            }
            res.data.forEach(mov => {
                let curReview = {};
                MovieService.getMovie(mov.ratingsPK.movieId).then(res => {
                    MovieCommentsService.getMovieCommentByMovieAndUser(mov.ratingsPK.movieId,currentUser.id).then(res2 => {
                        setLoading(false);
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
        if(movieActivity.activity.length>0){
            setLoading(false);
        }
    },[loading])
    const activityMap = movieActivity.activity.map( function(act){
        const comms = act.comments.map(function(comment){
            return (
                <>
                    <p className="com">&nbsp;&nbsp;&nbsp;<RiSubtractLine/> {comment}</p>
                </>
            );
        })
        return (
            <>
                <div className="activity">
                    <ActivityCardJsx {...act} comms={comms}/>
                </div>
            </>
        );
    });
    return (
        <div className="container">
            {loading?(<><AiOutlineLoading3Quarters className="loading"/></>):(<>
                {movieActivity.activity.length>0?(activityMap):(<><h3>No Recent Activity Found</h3></>)}
            </>)}
        </div>
    );
}

export default RecentActivityJsx;