import axios from 'axios';
import authHeader from './AuthHeaderService';

const baseUrl = 'http://localhost:8080/v1/movies/';

class MovieService{
    getMovie(movieId){
        return axios.get(baseUrl+`id/${movieId}`, { headers: authHeader() });
    }

    getAllMovies(page, size, sortedBy){
        return axios.get(baseUrl + `pageable?page=${page}&size=${size}&sort=${sortedBy}`, { headers: authHeader() }); 
    }

    getAllMoviesByGenre(genreName) {
        return axios.get(baseUrl + `genre/${genreName}`, { headers: authHeader() });
    }
}

export default new MovieService();