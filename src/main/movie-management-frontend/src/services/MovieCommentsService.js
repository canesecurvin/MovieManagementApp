import axios from 'axios';
import authHeader from './AuthHeaderService';

const baseUrl = 'http://localhost:8080/v1/movie-comments/';

class MovieCommentsService{
    getAllMovieComments(movieId){
        return axios.get(baseUrl+`${movieId}`, { headers: authHeader() });
    }

    addMovieComment(comment, timestamp, movieId, userId){
        const commentData = {
            comment: comment,
            movieId: movieId,
            userId: userId,
            timestamp: timestamp
        }
        return axios.post(baseUrl, commentData, { headers: authHeader() });
    }

    getMovieCommentByMovieAndUser(movieId,userId){
        return axios.get(baseUrl + `${movieId}/${userId}`, { headers: authHeader() });
    }

    updateComment(comId, comment, timestamp, movieId, userId){
        const commentData = {
            id: comId,
            comment: comment,
            timestamp: timestamp,
            movieId: movieId, 
            userId: userId
        };
        return axios.put(baseUrl, commentData, { headers: authHeader() });
    }

    deleteComment(comId){
        return axios.delete(baseUrl + comId, { headers: authHeader() });
    }
}

export default new MovieCommentsService();