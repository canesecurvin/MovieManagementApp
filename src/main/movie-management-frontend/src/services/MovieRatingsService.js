import axios from 'axios';
import authHeader from './AuthHeaderService';

const baseUrl = 'http://localhost:8080/v1/ratings/';

class MovieRatingsService{
    getMovieRatings(movieId){
        return axios.get(baseUrl+`movie/${movieId}`, { headers: authHeader() });
    }

    getMovieRatingByUserAndMovie(userId, movieId){
        return axios.get(baseUrl+`user/${userId}/${movieId}`, { headers: authHeader() });
    }

    getMovieRatingByUser(userId){
        return axios.get(baseUrl+`user/${userId}`, { headers: authHeader() });
    }

    rateMovie(userId, movieId, rating, review){
        const rateObject = {
            userId: userId,
            movieId: movieId, 
            rating: rating,
            review: review
        }
        return axios.post(baseUrl, rateObject, { headers: authHeader() });
    }
}

export default new MovieRatingsService();