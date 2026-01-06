import axios from 'axios';

const AUTH_REST_API_BASE_URL = "http://localhost:8080/api/auth";

export const registerAPICall = (registerObj) => axios.post(AUTH_REST_API_BASE_URL + '/register', registerObj);

export const loginAPICall = (usernameOrEmail, password) => axios.post(AUTH_REST_API_BASE_URL + '/login', {usernameOrEmail, password});

export const storeToken = (token) => localStorage.setItem("token", token);

export const getToken = () => localStorage.getItem("token");

export const saveLoggedInUser = (username, userRole) => {
    sessionStorage.setItem("authenticatedUser", username);
    sessionStorage.setItem("role", userRole);
}

export const isLoggedIn = () => {
    const username = sessionStorage.getItem("authenticatedUser");
    if(username == null){
        return false;
    }
    else{
        return true;
    }
}

export const getLoggedInUser = () => {
    const username = sessionStorage.getItem("authenticatedUser");
    return username;

}

export const logoutUser = () => {

    localStorage.clear();
    sessionStorage.clear();
}

export const isAdminUser = () => {

    const userRole = sessionStorage.getItem("role");

    if(userRole != null && userRole === "ROLE_ADMIN"){
        return true;
    }
    else{
        return false;
    }
}
