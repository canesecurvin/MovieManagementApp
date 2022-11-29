import axios from 'axios';

const baseUrl = 'http://localhost:8080/v1/users/';

class UserService{
    createUser(user){
        return axios.post(baseUrl+'register', user);
    }

    loginUser(user){
        return axios.post(baseUrl+'login', user);
    }
}

export default new UserService();