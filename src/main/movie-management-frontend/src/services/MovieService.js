import axios from 'axios';

const baseUrl = 'http://localhost:8080/v1/movies/';

class MovieService{
    getMovie(movieId){
        axios.get(baseUrl+`id/${movieId}`);
    }

    getAllMovies(){
        axios.get(baseUrl);
    }
}

export default new MovieService();