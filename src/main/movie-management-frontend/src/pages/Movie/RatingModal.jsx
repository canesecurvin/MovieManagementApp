import React, { useState } from 'react';
import Form from 'react-bootstrap/Form';
import Modal from 'react-bootstrap/Modal';
import Button from 'react-bootstrap/Button';
import MovieRatingsService from '../../services/MovieRatingsService';

function RatingModalJsx(props){
    const {show, handleClose, movieId} = props;
    const [rating, setRating] = useState({
        rating: 0,
        review: ''
    })
    function handleFieldChange(event) {
        setRating((values)=> ({
            ...values,
            [event.target.id]: event.target.value
        }));
    }

    function handleFormSubmit(event) {
        if (event) event.preventDefault();
        if (props.userRating==null) {
            MovieRatingsService.rateMovie(37, movieId, rating.rating, rating.review).then(res => {
            }).catch(err => {
                alert(err);
            });
        }else{
            alert('you already rated');
        }
        handleClose();
    }

    return (
        <>
            <Modal show={show} onHide={handleClose}>
                <Modal.Header closeButton>
                    <Modal.Title>Rate Movie</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form onSubmit={handleFormSubmit}>
                        <Form.Group className="mb-3 form-field" controlId="rating" onChange={handleFieldChange}>
                            <Form.Label>Rating</Form.Label>
                            <Form.Control required type="text" placeholder="Enter number 1 through 5"/>
                        </Form.Group>
                        <Form.Group className="mb-3 form-field" controlId="review" onChange={handleFieldChange}>
                            <Form.Label>Review</Form.Label>
                            <Form.Control required type="text" placeholder="I love this movie..."/>
                        </Form.Group>
                        <Button className="rate-button" variant="primary" type="submit">Rate</Button>
                    </Form>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleClose}>
                        Close
                    </Button>
                </Modal.Footer>
            </Modal>
        </>
    );
}

export default RatingModalJsx;