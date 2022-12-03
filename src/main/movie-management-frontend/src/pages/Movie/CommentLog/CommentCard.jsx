import React from "react";
import Card from 'react-bootstrap/Card';
import Button from 'react-bootstrap/Button';
import AuthService from "../../../services/AuthService";

function CommentCardJsx(props){
    const isCommentOwner = AuthService.getCurrentUser().id === props.userId;
    return (
        <div>
            <Card>
                <Card.Body className="comment-card-body">
                    <Card.Text>
                        {props.timestamp}<br/>
                        {props.comment}<br/>
                    </Card.Text>
                    {isCommentOwner ? (<div className="action-buttons">
                        <Button className="update-button" variant="primary">Update</Button>
                        <Button className="delete-button" variant="danger">Delete</Button>   
                    </div>): (<></>)}
                </Card.Body>
            </Card>
        </div>
    );
}

export default CommentCardJsx;