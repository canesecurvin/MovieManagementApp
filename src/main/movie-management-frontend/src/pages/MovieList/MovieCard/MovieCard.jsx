import React from "react";
import Card from 'react-bootstrap/Card';
import Button from 'react-bootstrap/Button';
import {useNavigate} from "react-router-dom";

function MovieCardJsx(props){
    const navigate = useNavigate();
    function showSingleMovie(){
        navigate(`/movie/${props.id}`);
    }
    return (
        <div>
            <Card>
            <Card.Body>
                <Card.Title>{props.movieName}</Card.Title>
                <Card.Text>
                    <b>Year:</b> {props.releaseYear}<br/>
                    <b>Duration:</b> {props.movieLength}<br/>
                    <b>Rating:</b> {props.rating}<br/>
                </Card.Text>
                <Button variant="primary" onClick={() => { showSingleMovie()}}>Movie Details</Button>
            </Card.Body>
            </Card>
        </div>
    );
}

export default MovieCardJsx;