import React, {useEffect, useState} from "react";
import Dropdown from 'react-bootstrap/Dropdown';
import DropdownButton from 'react-bootstrap/DropdownButton';
import MovieCardJsx from "./MovieCard/MovieCard.jsx";
import MovieService from "../../services/MovieService.js";
import GenreService from "../../services/GenreService.js";
import Placeholder from 'react-bootstrap/Placeholder';
import './MovieList.css';


function MovieListJsx(){
    const [moviesList, setMoviesList] = useState([]);
    const [genres, setGenres] = useState([]);
    const [selectedGenre, setSelectedGenre] = useState("");
    const [genreChanged, setGenreSaved] = useState(false);
    const [loading, setLoading] = useState(true);
    let numOfPages = 4;
    let page = 0;
    let size = 3;
    let sortedBy = "movieName"
    useEffect(()=>{
        const getMovies = ()=> {
            MovieService.getAllMovies(page, size, sortedBy).then(res => {
                setLoading(false);
                setMoviesList([...moviesList, ...res.data.content]);
                res.data.content.forEach(mov=>{
                    moviesList.push(mov);
                })
                numOfPages = res.data.totalPages;
                page++;
                if (page<numOfPages){
                    getMovies();
                }
            }).catch(error => {
                console.log("error",error);
            });
        }
        const getGenres = ()=>{
            GenreService.getAllGenres().then(res => {
                setGenres([...res.data]);
            })
        }
        getMovies();
        getGenres();
    }, [moviesList.movies])
    const renderMoviesByGenre = (genreName) => {
        setLoading(true);
        setMoviesList([]);
        page = 0;
        const getMovies = () => {
            MovieService.getAllMovies(page, size, sortedBy).then(res => {
                setLoading(false);
                setMoviesList([...moviesList, ...res.data.content]);
                res.data.content.forEach(mov=>{
                    moviesList.push(mov);
                })
                numOfPages = res.data.totalPages;
                page++;
                if (page<numOfPages){
                    getMovies();
                }
            }).catch(error => {
                console.log("error",error);
            }); 
        }
        if (genreName === ""){
            getMovies();
            setSelectedGenre("");
        } 
        MovieService.getAllMoviesByGenre(genreName).then(res => {
            setLoading(false);
            setMoviesList([...res.data]);
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
            {loading?(<div className="movie-list" id="list">
                {[...Array(15)].map((i)=> {
                    return (
                    <div key={i} className="movie-card" style={{width: '25%', height: '10%', margin: '2.5%',  display: 'flex', flexDirection: 'column', alignItems: 'center' }}>
                        <Placeholder className="poster" />
                        <Placeholder bg="secondary" style={{marginTop: '1.5%', width: '25%', height: '10%'}}/>
                        <Placeholder bg="secondary" style={{marginTop: '1.5%', width: '25%', height: '10%'}}/>
                        <Placeholder.Button style={{marginTop: '1.5%', width: '30%', height: '5%'}}/>
                    </div>)
                })}
            </div>):(<>
                <div className="movie-list" id="list">
                    {moviesList.map(function(mov, i){
                        return (
                            <div key={i} className="movie-card">
                                <MovieCardJsx key={i} {...mov}/>
                            </div>
                        );
                    })}
                </div>
            </>)}
        </div>
    );
}

export default MovieListJsx;