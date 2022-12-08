import React, {useEffect, useState} from "react";
import {useNavigate} from 'react-router-dom';
import { ImageUrls } from "../../../assets/image-urls";
import Card from 'react-bootstrap/Card';
import Button from 'react-bootstrap/Button';
function FavoriteCardJsx(props){
    const {removeFromFavorites} = props;
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
        randomizePoster(props.movie.id);
    },[])

    return (
        <Card>
            <Card.Header><b>{props.movie.movieName}</b></Card.Header>
            <Card.Body className="movie-card-body">
                <div className="activity-poster" id={`movie-${props.movie.id}`}></div>
            </Card.Body>
            <Card.Footer>
                <Button className="remove-button" variant="danger" onClick={() => { removeFromFavorites(props.id)}}>Remove</Button>
                <Button className="button" variant="primary" onClick={() => { showSingleMovie(props.movie.id)}}>View Movie</Button>
            </Card.Footer>
        </Card>
    )
}

export default FavoriteCardJsx;