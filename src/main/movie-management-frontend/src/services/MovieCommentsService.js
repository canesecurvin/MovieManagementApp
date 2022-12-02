import axios from 'axios';

const baseUrl = 'http://localhost:8080/v1/movie-comments/';

class MovieCommentsService{
    getAllMovieComments(movieId){
        return axios.get(baseUrl+`${movieId}`,{
            headers: {
                'Access-Control-Allow-Headers': '*'
              }
          });
    }

    addMovieComment(comment, timestamp, movieId, userId){
        const movieData = {
            comment: comment,
            movieId: movieId,
            userId: userId,
            timestamp: timestamp
        }
        return axios.post(baseUrl, movieData, {
            headers: {
                'Access-Control-Allow-Headers': '*'
            }
        });
    }

    getMovieCommentByMovieAndUser(movieId,userId){
        return axios.get(baseUrl + `${movieId}/${userId}`);
    }
}

export default new MovieCommentsService();