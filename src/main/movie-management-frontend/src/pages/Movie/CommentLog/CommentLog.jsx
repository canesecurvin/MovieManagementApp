import React from "react";
import CommentCardJsx from "./CommentCard.jsx";

const comments = [
    {time_stamp: 'Jan 17,2021 7:39', user: 'Jane', comment: 'I love this movie!'},
    {time_stamp: 'Jan 17,2021 7:39', user: 'Jane', comment: 'I love this movie!'}
]
function CommentLogJsx(){
    return (
        <div>
            {comments.map(function(com,i){
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