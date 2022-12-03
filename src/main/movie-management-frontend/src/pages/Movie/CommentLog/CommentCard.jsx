import React, {useState} from "react";
import Card from 'react-bootstrap/Card';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import AuthService from "../../../services/AuthService";
import MovieCommentsService from "../../../services/MovieCommentsService";

function CommentCardJsx(props){
    const isCommentOwner = AuthService.getCurrentUser().id === props.userId;
    const [editMode, setEditMode] = useState(false);
    const [updatedComment, setUpdatedComment] = useState("");

    const handleUpdateComment = () => {
        setEditMode(false);
        MovieCommentsService.updateComment(props.id, updatedComment, 
            props.timestamp, props.movieId, props.userId).then(res => {
            }).catch(error => {console.log(error);})
        setUpdatedComment("");
    }
    const handleFieldChange = (event) => {
        setUpdatedComment(event.target.value);
    }
    const handleDelete = () => {
        MovieCommentsService.deleteComment(props.id).then(res => {
        }).catch(error => {console.log(error);})
    }
    return (
        <div>
            <Card>
                <Card.Body className="comment-card-body">
                    {!editMode ? (
                        <Card.Text>
                            {props.timestamp}<br/>
                            {updatedComment.length === 0 ? props.comment : updatedComment}<br/>
                        </Card.Text>)
                    : (
                        <Form className="update-form" onSubmit={handleUpdateComment}>
                            <Form.Group className="mb-3 comment-field" controlId="comment" onChange={handleFieldChange}>
                                <Form.Control required defaultValue={props.comment}/>
                            </Form.Group>
                            <Button className="update-button" variant="primary" onClick={() => {handleUpdateComment()}}>Update</Button>
                            <Button className="update-button" variant="secondary" onClick={() => {setEditMode(false);}}>Cancel</Button>
                        </Form>
                    )}
                    {isCommentOwner && !editMode ? (<div className="action-buttons">
                        <Button className="edit-button" variant="primary" onClick={() => {setEditMode(true);}}>Edit</Button>
                        <Button className="delete-button" variant="danger" onClick={() => {handleDelete()}}>Delete</Button>   
                    </div>): (<></>)}
                </Card.Body>
            </Card>
        </div>
    );
}

export default CommentCardJsx;