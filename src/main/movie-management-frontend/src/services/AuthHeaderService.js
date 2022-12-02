export default function authHeader(){
    const currentUser = JSON.parse(localStorage.getItem("currentUser"));
    const token = JSON.parse(localStorage.getItem("token"));

    if (currentUser && token){
        return { Authorization: 'Bearer ' + token.token };
    } else {
        return {};
    }
}