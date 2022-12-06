import React, {useEffect, useState} from "react";
import Button from 'react-bootstrap/Button';
import RatingStarsJsx from "../../../components/RatingStars";
import {useNavigate} from "react-router-dom";
import './MovieCard.css';
import { ImageUrls } from "../../../assets/image-urls";

function MovieCardJsx(props){
    const navigate = useNavigate();
    const images = ImageUrls['movie-posters'];
    const [posterImage, setPosterImage] = useState('');
    function showSingleMovie(){
        navigate(`/movie/${props.id}`, {state:{image:posterImage}});
    }
    const randomizePoster = (id) => {
        let img = document.getElementById(`movie-${id}`);
        let image = images[Math.floor(Math.random()*images.length)]
        img.style.backgroundImage = `url(${image})`;
        setPosterImage(image);
    }
    useEffect(()=> {
        randomizePoster(props.id);
    }, [])
    return (
        <div className="movie">
            <div className="poster" id={`movie-${props.id}`}></div>
            <RatingStarsJsx rating={props.rating}/>
            <p>{props.movieName}</p>
            <Button variant="primary" onClick={() => { showSingleMovie()}}>Movie Details</Button>
        </div>
    );
}

export default MovieCardJsx;