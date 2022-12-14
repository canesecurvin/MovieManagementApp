import React, {useEffect, useState}  from "react";
import CommentCardJsx from "./CommentCard.jsx";
import AddCommentJsx from "./AddComment";
import MovieCommentsService from "../../../services/MovieCommentsService.js";
import AuthService from "../../../services/AuthService.js";
import './CommentLog.css';

function CommentLogJsx(props){
    const currentUser = AuthService.getCurrentUser();
    const [commentSaved, setCommentSaved] = useState(false);
    const [values, setValues] = useState({
        comments: []
    })
    useEffect(()=> {
        MovieCommentsService.getAllMovieComments(props.id).then(res => {
            let sortedData = res.data.sort((a,b)=>{
                return Date.parse(b.timestamp.slice(0, 19).replace(' ', 'T')) - Date.parse(a.timestamp.slice(0, 19).replace(' ', 'T'))
            })
            setValues(()=> ({
                comments: sortedData
            }));
        }).catch(error => {
            console.log(error);
        })
    },[values.comments, commentSaved])
    let onSuccess = () => {
        return (<></>);
    }
    const [comment, setComment] = useState('');

    function handleFieldChange(event) {
        setComment(()=> (event.target.value));
    }

    function handleFormSubmit(event) {
        const timestamp = new Date().toISOString().slice(0, 19).replace('T', ' ');
        if (event) event.preventDefault();
        MovieCommentsService.addMovieComment(comment, timestamp, props.id, currentUser.id).then(res => {
            setCommentSaved(true);
            setTimeout(() => {
                setCommentSaved(false);
              }, "3000")
        }).catch(error => {
            console.log(error);
        }).then(() => {
            onSuccess('Comment Saved');
        })
        event.target.reset();
    }
    return (
        <div>
            <AddCommentJsx movieId={props.id} handleFieldChange={handleFieldChange} handleFormSubmit={handleFormSubmit}/>
            {commentSaved ? (<div className="success-message"><p>Comment Saved</p></div>) : (<></>)}
            {values.comments.map(function(com,i){
                return (
                    <div key={i}>
                        <CommentCardJsx {...com}/>
                    </div>
                );
            })}
        </div>
    );
}

export default CommentLogJsx;