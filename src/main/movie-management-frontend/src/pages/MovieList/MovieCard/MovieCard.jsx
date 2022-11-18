import React from "react";
import Card from 'react-bootstrap/Card';

function MovieCard(props){
    return (
        <div>
            <Card>
            <Card.Img variant="top" src="holder.js/100px180" />
            <Card.Body>
                <Card.Title>{props.movieName}</Card.Title>
                <Card.Text>
                    <p>Year:{props.year}</p>
                    <p>Director:{props.director}</p>
                    <p>Cast:{props.cast}</p>
                </Card.Text>
                <Button variant="primary">Movie Details</Button>
            </Card.Body>
            </Card>
        </div>
    );
}

export default MovieCard;