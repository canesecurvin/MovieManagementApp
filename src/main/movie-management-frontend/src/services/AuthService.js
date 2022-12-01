import UserService from "./UserService";
import {useNavigate} from 'react-router-dom';

class AuthService {
    currentUser = {};
    //eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJNb3ZpZSBBcHAgVGVhb…ssYmWkwYJ_rPs9IMviKdiOg:canesecurvin@gmail.com:35
    setCurrentUser(loginData){
        let resArr = loginData.accessToken.split(':');
        console.log(loginData);
        this.currentUser = {id: resArr[2], displayName: resArr[1]}
        localStorage.setItem("currentUser", this.currentUser)
        this.setToken(resArr[0], loginData.tokenType);
        console.log(this.currentUser, localStorage.getItem("token"));
    }

    getCurrentUser(){
        return localStorage.getItem("currentUser");
    }

    logout(){
        localStorage.removeItem("currentUser");
    }

    setToken(token, tokenType){
        localStorage.setItem("token", {token: token, tokenType: tokenType});
    }
}

export default new AuthService();