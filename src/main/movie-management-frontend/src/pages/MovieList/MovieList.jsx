import React, {useEffect, useState} from "react";
import Dropdown from 'react-bootstrap/Dropdown';
import DropdownButton from 'react-bootstrap/DropdownButton';
import MovieCardJsx from "./MovieCard/MovieCard.jsx";
import MovieService from "../../services/MovieService.js";
import GenreService from "../../services/GenreService.js";
import './MovieList.css';


function MovieListJsx(){
    const [moviesList, setMoviesList] = useState({
        movies: []
    });
    const [genres, setGenres] = useState([]);
    const [selectedGenre, setSelectedGenre] = useState("");
    const [genreChanged, setGenreSaved] = useState(false);
    useEffect(()=>{
        const getMovies = ()=> {
            MovieService.getAllMovies().then(res => {
                setMoviesList(()=> ({
                    movies: [...res.data]
                }));
            }).catch(error => {
                console.log(error);
            });
        }
        const getGenres = ()=>{
            GenreService.getAllGenres().then(res => {
                setGenres([...res.data]);
            })
        }
        getMovies();
        getGenres();
    }, [])
    const renderMoviesByGenre = (genreName) => {
        if (genreName === ""){
            MovieService.getAllMovies().then(res => {
                setMoviesList(()=> ({
                    movies: [...res.data]
                }));
                setSelectedGenre(genreName);
                setGenreSaved(true);
                setTimeout(() => {
                    setGenreSaved(false);
                }, "1500")
            }).catch(error => {
                console.log(error);
            });
        } 
        MovieService.getAllMoviesByGenre(genreName).then(res => {
            setMoviesList(()=> ({
                movies: [...res.data]
            }));
            setSelectedGenre(genreName);
            setGenreSaved(true);
            setTimeout(() => {
                setGenreSaved(false);
            }, "1500")
        }).catch(error => {console.log(error);})
    }
    return (
        <div className="movie-list-jsx">
            <div className="genre-dropdown">
                <DropdownButton id="dropdown-basic-button" title="Filter By Genre">
                    <Dropdown.Item onClick={() => {renderMoviesByGenre("")}}>All</Dropdown.Item>
                    {genres.map(function(genre, i){
                        return (
                            <>
                                <Dropdown.Item key={i} onClick={() => {renderMoviesByGenre(genre.genre)}}>{genre.genre}</Dropdown.Item>
                            </>
                        )
                    })}
                </DropdownButton>
                {genreChanged ? (<div className="genre-success-message"><p>Genre Changed</p></div>) : (<></>)}
            </div>
            {selectedGenre!=""?(<><h3 className="genre-name">{selectedGenre}</h3></>):(<></>)}
            <div className="movie-list">
                {moviesList.movies.map(function(mov, i){
                    return (
                        <div key={i} className="movie-card">
                            <MovieCardJsx key={i} {...mov}/>
                        </div>
                    );
                })}
            </div>
        </div>
    );
}

export default MovieListJsx;