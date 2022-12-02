import UserService from "./UserService";
import {useNavigate} from 'react-router-dom';

class AuthService {
    currentUser = {};
    setCurrentUser(loginData){
        let resArr = loginData.accessToken.split(':');
        this.currentUser = {id: parseInt(resArr[2]), displayName: resArr[1]}
        localStorage.setItem("currentUser", JSON.stringify(this.currentUser));
        this.setToken(resArr[0], loginData.tokenType);
    }

    getCurrentUser(){
        return JSON.parse(localStorage.getItem("currentUser"));
    }

    isLoggedIn(){
        if (localStorage.getItem("currentUser")==null){
            return false;
        }
        return true;
    }

    logout(){
        localStorage.removeItem("currentUser");

    }

    setToken(token, tokenType){
        localStorage.setItem("token", JSON.stringify({token: token, tokenType: tokenType}));
    }
}

export default new AuthService();