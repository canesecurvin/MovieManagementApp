import React from "react";
import './Tabs.css';

const userActivity = [
    {movie: 'Titanic', stars: 5, commments: [
        'loved it',
        'I need my Jack in this world'
    ]},
    {movie: 'Monsters Inc', stars: 3.5, commments: [
        'great for the kids',
        'everyone has a Boo in their family'
    ]},
    {movie: 'Hostel', stars: 2, commments: [
        'too gruesome for me!',
    ]}
]

const activityMap = userActivity.map(function (act){
    return (
        <>
            <div className="activity">
                <p><b>Movie:</b> {act.movie}</p>
                <p><b>Stars:</b> {act.stars}</p>
                {act.commments.map(function(comment){
                    return (
                        <>
                            <p><b>Comment:</b> {comment}</p>
                        </>
                    );
                })}
                <br/>
            </div>
        </>
    );
});
function RecentActivityJsx(){
    return (
        <div className="container">
            {activityMap}
        </div>
    );
}

export default RecentActivityJsx;