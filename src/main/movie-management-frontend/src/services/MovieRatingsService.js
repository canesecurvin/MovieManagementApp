import axios from 'axios';

const baseUrl = 'http://localhost:8080/v1/ratings/';

class MovieRatingsService{
    getMovieRatings(movieId){
        return axios.get(baseUrl+`movie/${movieId}`);
    }

    getMovieRatingByUserAndMovie(userId, movieId){
        return axios.get(baseUrl+`user/${userId}/${movieId}`);
    }

    getMovieRatingByUser(userId){
        return axios.get(baseUrl+`user/${userId}`);
    }

    rateMovie(userId, movieId, rating, review){
        const rateObject = {
            userId: userId,
            movieId: movieId, 
            rating: rating,
            review: review
        }
        return axios.post(baseUrl, rateObject);
    }
}

export default new MovieRatingsService();