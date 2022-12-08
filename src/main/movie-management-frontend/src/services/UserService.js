import axios from 'axios';
import authHeader from './AuthHeaderService';

const baseUrl = 'http://localhost:8080/v1/users/';

class UserService{
    createUser(user){
        return axios.post(baseUrl+'register', user);
    }

    loginUser(user){
        return axios.post(baseUrl+'login', user);
    }

    updateUserBasicInfo(userBasicInfo){
        return axios.put(baseUrl+`user/${userBasicInfo.id}`, userBasicInfo, { headers: authHeader() });
    }

    updateUserPassword(userId, passwordObject) {
        const passwords = {
            oldPassword: passwordObject.oldPassword,
            newPassword: passwordObject.newPassword
        }
        return axios.put(baseUrl+`password/${userId}`, passwords, { headers: authHeader() })
    }
}

export default new UserService();