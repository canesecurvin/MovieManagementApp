import React from 'react';
import { FaStar, FaRegStar } from "react-icons/fa";
import './Components.css';

function RatingStarsJsx(props){
    try {
        const displayFullStars = [...Array(Math.floor(props.rating))].map((i)=> {
            return (
                <div key={i}>
                    <FaStar key={i}/>
                </div>
            )
        });
        const displayEmptyStars = [...Array(Math.round(5 - props.rating))].map((i)=> {
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
    }catch(e){console.log(e)}
}

export default RatingStarsJsx;