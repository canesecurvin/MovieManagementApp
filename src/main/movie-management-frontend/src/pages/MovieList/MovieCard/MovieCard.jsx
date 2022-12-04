import React from "react";
import Card from 'react-bootstrap/Card';
import Button from 'react-bootstrap/Button';
import RatingStarsJsx from "../../../components/RatingStars";
import {useNavigate} from "react-router-dom";
import './MovieCard.css';

function MovieCardJsx(props){
    const navigate = useNavigate();
    function showSingleMovie(){
        navigate(`/movie/${props.id}`);
    }
    return (
        <div>
            <Card>
            <Card.Body>
                <Card.Title className="movie-name">{props.movieName}</Card.Title>
                <Card.Text>
                    <RatingStarsJsx rating={props.rating}/>
                    <b>Year:</b> {props.releaseYear}<br/>
                    <b>Duration:</b> {props.movieLength}<br/>
                </Card.Text>
                <Button variant="primary" onClick={() => { showSingleMovie()}}>Movie Details</Button>
            </Card.Body>
            </Card>
        </div>
    );
}

export default MovieCardJsx;