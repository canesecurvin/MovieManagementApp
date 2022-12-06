import axios from 'axios';
import authHeader from './AuthHeaderService';

const baseUrl = 'http://localhost:8080/v1/genres/';

class GenreService{
    getAllGenres(){
        return axios.get(baseUrl, { headers: authHeader() })
    }
}

export default new GenreService();