import axios from "axios";
import authHeader from "./AuthHeaderService";

const baseUrl = 'http://localhost:8080/v1/favorites';

class FavoritesService{

    getUserFavorites(userId){
        return axios.get(baseUrl + `/${userId}`, {headers: authHeader()});
    }

   getFavoriteByUserIdAndMovieId(userId, movieId){
        return axios.get(baseUrl +`/${userId}/movie/${movieId}`, {headers: authHeader()});
    }

    addUserFavorites(userId, movieId){
        const favorite = {
            userId: userId,
            movieId: Number(movieId)
        }
        return axios.post(baseUrl, favorite, {headers: authHeader()});
    }

    deleteUserFavorite(favoriteId, userId){
        return axios.delete(baseUrl + `/${favoriteId}/user/${userId}`, {headers: authHeader()})
    }

    deleteFavoriteByMovieIdAndUserId(movieId, userId){
        return axios.delete(baseUrl +`/movie/${movieId}/user/${userId}`, {headers: authHeader()});
    }
}

export default new FavoritesService();