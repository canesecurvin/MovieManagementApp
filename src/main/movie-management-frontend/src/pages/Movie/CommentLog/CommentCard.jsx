import React from "react";
import Card from 'react-bootstrap/Card';

function CommentCardJsx(props){
    return (
        <div>
            <Card>
                <Card.Body>
                    <Card.Text>
                        {props.timestamp}<br/>
                        {props.comment}<br/>
                    </Card.Text>
                </Card.Body>
            </Card>
        </div>
    );
}

export default CommentCardJsx;