import React from "react";
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

const userActivity = [
    {movie: 'Titanic', stars: 5, commments: [
        'loved it',
        'I need my Jack in this world'
    ]},
    {movie: 'Monsters Inc', stars: 3.5, commments: [
        'great for the kids',
        'everyone has a Boo in their family'
    ]},
    {movie: 'Hostel', stars: 2, commments: [
        'too gruesome for me!',
    ]}
]

const activityMap = userActivity.map(function (act){
    return (
        <>
            <div className="activity">
                <p><b>Movie:</b> {act.movie}</p>
                <p><b>Stars:</b> {act.stars}</p>
                {act.commments.map(function(comment){
                    return (
                        <>
                            <p><b>Comment:</b> {comment}</p>
                        </>
                    );
                })}
                <br/>
            </div>
        </>
    );
});
function RecentActivityJsx(){
    const navigate = useNavigate();
    function showSingleMovie(id){
        navigate(`/movie/${id}`);
    }
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
                        curReview.id = mov.ratingsPK.movieId;
                        curReview.rating = mov.rating;
                        curReview.review = mov.review;
                        curReview.movieName = res.data.movieName;
                        console.log('com', res2.data, 'res', res2)
                        let comList = []
                        res2.data.forEach(com => {
                            comList.push(com.comment);
                        })
                        curReview.comments = comList;
                        console.log('comlist', curReview, 'ccom', curReview.comments);
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