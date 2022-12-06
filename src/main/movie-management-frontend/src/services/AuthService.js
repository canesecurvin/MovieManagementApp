import UserService from "./UserService";
import {useNavigate} from 'react-router-dom';

class AuthService {
    currentUser = {};
    setCurrentUser(loginData){
        this.currentUser = {
            id: loginData.id,
            email: loginData.email,
            firstName: loginData.firstName,
            lastName: loginData.lastName,
            displayName: loginData.displayName
        }
        localStorage.setItem("currentUser", JSON.stringify(this.currentUser));
        this.setToken(loginData.accessToken, loginData.tokenType);
    }

    updateCurrentUser(updatedUser) {
        this.currentUser = {
            id: this.getCurrentUser().id,
            email: updatedUser.email,
            firstName: updatedUser.firstName,
            lastName: updatedUser.lastName,
            displayName: updatedUser.displayName
        }
        localStorage.setItem("currentUser", JSON.stringify(this.currentUser));
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