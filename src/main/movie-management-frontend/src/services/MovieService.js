import axios from 'axios';
import authHeader from './AuthHeaderService';

const baseUrl = 'http://localhost:8080/v1/movies/';

class MovieService{
    getMovie(movieId){
        return axios.get(baseUrl+`id/${movieId}`, { headers: authHeader() });
    }

    getAllMovies(){
        console.log(authHeader())
        try {
            return axios.get(baseUrl, { headers: authHeader() });  
        }catch(e){console.log(e)}
    }
}

export default new MovieService();