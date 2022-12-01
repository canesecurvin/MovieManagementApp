export default function authHeader(){
    const currentUser = JSON.parse(localStorage.getItem("currentUser"));
    const token = localStorage.getItem("jwtToken");

    if (currentUser && token){
        return { Authorization: 'Bearer ' + token };
    } else {
        return {};
    }
}