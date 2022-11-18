import React from "react";
import Card from 'react-bootstrap/Card';
import Button from 'react-bootstrap/Button';

function MovieCardJsx(props){
    return (
        <div>
            <Card>
            <Card.Body>
                <Card.Title>{props.movieName}</Card.Title>
                <Card.Text>
                    <b>Year:</b> {props.year}<br/>
                    <b>Director:</b> {props.director}<br/>
                    <b>Cast:</b> {props.cast}<br/>
                </Card.Text>
                <Button variant="primary">Movie Details</Button>
            </Card.Body>
            </Card>
        </div>
    );
}

export default MovieCardJsx;