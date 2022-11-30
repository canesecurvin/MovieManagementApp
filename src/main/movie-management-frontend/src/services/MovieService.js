import axios from 'axios';

const baseUrl = 'http://localhost:8080/v1/movies/';

class MovieService{
    getMovie(movieId){
        return axios.get(baseUrl+`id/${movieId}`);
    }

    getAllMovies(){
        return axios.get(baseUrl);
    }
}

export default new MovieService();