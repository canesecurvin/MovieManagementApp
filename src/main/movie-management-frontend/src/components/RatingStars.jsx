import React from 'react';
import { FaStar, FaRegStar } from "react-icons/fa";
import './Components.css';

function RatingStarsJsx(props){
    const displayFullStars = [...Array(props.rating)].map((i)=> {
        return (
            <div key={i}>
                <FaStar key={i}/>
            </div>
        )
    });
    const displayEmptyStars = [...Array(5 - props.rating)].map((i)=> {
        return (
            <div key={i}>
                <FaRegStar key={i}/>
            </div>
        )
    });
    return (
        <div className='ratings'>
            {displayFullStars}
            {displayEmptyStars}
        </div>
    )
}

export default RatingStarsJsx;