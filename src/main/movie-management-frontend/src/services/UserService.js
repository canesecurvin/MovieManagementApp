import axios from 'axios';

const baseUrl = 'http://localhost:8080/v1/users/';

class UserService{
    createUser(user){
        console.log('create user service', user)
        return axios.post(baseUrl+'register', user);
    }

    loginUser(user){
        axios.get(baseUrl+'login', user);
    }
}

export default new UserService();