import axios from 'axios';
import authHeader from './AuthHeaderService';

const baseUrl = 'http://localhost:8080/v1/movie-comments/';

class MovieCommentsService{
    getAllMovieComments(movieId){
        return axios.get(baseUrl+`${movieId}`, { headers: authHeader() });
    }

    addMovieComment(comment, timestamp, movieId, userId){
        const movieData = {
            comment: comment,
            movieId: movieId,
            userId: userId,
            timestamp: timestamp
        }
        return axios.post(baseUrl, movieData, { headers: authHeader() });
    }

    getMovieCommentByMovieAndUser(movieId,userId){
        return axios.get(baseUrl + `${movieId}/${userId}`, { headers: authHeader() });
    }
}

export default new MovieCommentsService();