import React, { useState } from 'react';
import Form from 'react-bootstrap/Form';
import Modal from 'react-bootstrap/Modal';
import Button from 'react-bootstrap/Button';
import MovieRatingsService from '../../services/MovieRatingsService';
import AuthService from '../../services/AuthService';

function RatingModalJsx(props){
    const currentUser = AuthService.getCurrentUser();
    const {show, handleClose, movieId, setNewRating, setNewRatingSaved} = props;
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
            MovieRatingsService.rateMovie(currentUser.id, movieId, rating.rating, rating.review).then(res => {
            }).catch(error => {
                console.log(error);
            });
        }
        setNewRatingSaved(true);
        setNewRating(rating.rating)
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
                            <Form.Control as="select">
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                                <option value="">3</option>
                            </Form.Control>
                        </Form.Group>
                        <Form.Group className="mb-3 rating-form-field" controlId="review" onChange={handleFieldChange}>
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