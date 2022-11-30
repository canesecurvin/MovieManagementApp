import React, {useState} from 'react';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import MovieCommentsService from '../../../services/MovieCommentsService';
import './CommentLog.css';

function AddCommentJsx(props){
    const [values, setValues] = useState({
        comment: ''
      });

    function handleFieldChange(event) {
        setValues((values)=> ({
            ...values,
            [event.target.id]: event.target.value
        }));
    }

    function handleFormSubmit(event) {
        const timestamp = new Date().toISOString().slice(0, 19).replace('T', ' ');
        if (event) event.preventDefault();
        MovieCommentsService.addMovieComment(values.comment, timestamp, props.movieId, 35).then(res => {
        }).catch(err => {
            alert(err);
        })
    }

    return (
        <div className="add-container">
            <h3>Add Comment</h3>
            <Form className="form" onSubmit={handleFormSubmit}>
                <Form.Group className="mb-3 form-field" controlId="comment" onChange={handleFieldChange}>
                    <Form.Control required type="text" placeholder="This movie was great!"/>
                </Form.Group>
                <Button className="submit-button" variant="primary" type="submit">Submit</Button>
            </Form>
        </div>
    )
}

export default AddCommentJsx;