import React, {useEffect, useState}  from "react";
import CommentCardJsx from "./CommentCard.jsx";
import MovieCommentsService from "../../../services/MovieCommentsService.js";

function CommentLogJsx(props){
    const [values, setValues] = useState({
        comments: []
    })
    useEffect(()=> {
        MovieCommentsService.getAllMovieComments(props.id).then(res => {
            setValues(()=> ({
                comments: res.data
            }));
        }).catch(err => {
            alert(err);
        })
    }, [])
    return (
        <div>
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