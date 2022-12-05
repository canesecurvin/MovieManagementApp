import React, {useEffect, useState} from 'react';
import {useNavigate} from "react-router-dom";
import RatingStarsJsx from "../../../components/RatingStars";
import Card from 'react-bootstrap/Card';
import Button from "react-bootstrap/Button";
import { ImageUrls } from '../../../assets/image-urls';

function ActivityCardJsx(props){
    const images = ImageUrls['movie-posters'];
    const navigate = useNavigate();
    const [posterImage, setPosterImage] = useState('');
    function showSingleMovie(id){
        navigate(`/movie/${id}`, {state:{image:posterImage}});
    }
    const randomizePoster = (id) => {
        let img = document.getElementById(`movie-${id}`);
        let image = images[Math.floor(Math.random()*images.length)]
        img.style.backgroundImage = `url(${image})`;
        setPosterImage(image);
    }
    useEffect(()=>{
        randomizePoster(props.id);
    },[])
    return (
        <>
        <Card>
            <Card.Header><b>{props.movieName}</b></Card.Header>
            <Card.Body className="movie-card-body">
                <div className="activity-poster" id={`movie-${props.id}`}></div>
                <div className="activity-info">
                    <RatingStarsJsx className="stars" rating={props.rating} />
                    <p className="review"><b>Review:</b> {props.review}</p>
                    <div className="comments">
                        <p><b>Comments:</b></p>
                        {props.comms}
                    </div>
                </div>
            </Card.Body>
            <Card.Footer>
                <Button className="button" variant="primary" onClick={() => { showSingleMovie(props.id)}}>View Movie</Button>
            </Card.Footer>
        </Card>
        </>
    )
}

export default ActivityCardJsx;