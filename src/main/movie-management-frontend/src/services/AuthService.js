import UserService from "./UserService";
import {useNavigate} from 'react-router-dom';

class AuthService {
    currentUser = {};
    setCurrentUser(loginData){
        let resArr = loginData.accessToken.split(':');
        console.log(loginData);
        this.currentUser = {id: resArr[2], displayName: resArr[1]}
        localStorage.setItem("currentUser", this.currentUser)
        console.log(localStorage.getItem("currentUser"))
        this.setToken(resArr[0], loginData.tokenType);
        console.log(this.currentUser, localStorage.getItem("token"));
    }

    getCurrentUser(){
        return localStorage.getItem("currentUser");
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
        localStorage.setItem("token", {token: token, tokenType: tokenType});
    }
}

export default new AuthService();