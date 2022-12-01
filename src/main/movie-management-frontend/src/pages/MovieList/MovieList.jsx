import React, {useEffect, useState} from "react";
import MovieCardJsx from "./MovieCard/MovieCard.jsx";
import MovieService from "../../services/MovieService.js";
import './MovieList.css';


function MovieListJsx(){
    const [moviesList, setMoviesList] = useState({
        movies: []
    });
    useEffect(()=>{
        const getMovies = ()=> {
            MovieService.getAllMovies().then(res => {
                setMoviesList(()=> ({
                    movies: [...res.data]
                }));
            }).catch(err => {
                alert('couldnt retrieve movies');
            });
        }
        getMovies();
    }, [])
    return (
        <>
            {moviesList.movies.map(function(mov, i){
                return (
                    <div key={i} className="movie-card">
                        <MovieCardJsx key={i} {...mov}/>
                    </div>
                );
            })}
        </>
    );
}

export default MovieListJsx;