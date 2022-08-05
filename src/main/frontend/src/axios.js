// import axios from "axios";
// let refresh = false;
//
// axios.interceptors.response.use(resp => resp, async error => {
//         if (error.response.status === 401 && !refresh) {
//         refresh = true;
//
//         const response = await axios.post('http://localhost:8080/auth/refreshToken', {});
//
//         if (response.status === 200) {
//
//             axios.defaults.headers.common['Authorization'] = `Bearer ${response.data['token']}`;
//             sessionStorage.setItem("token", response.data['token'])
//             return axios(error.config);
//         }
//     }
//     refresh = false;
//     return error;
// });

import axios from "axios";
import {useEffect} from "react";

let refresh = false;

// -------  zachaszowane, bo nie dzila
//
// useEffect(() => {
//     setTokenInAxiosHeaders();
// })
//
// function setTokenInAxiosHeaders() {
//     let token = sessionStorage.getItem("token");
//     if (token) {
//         axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
//     } else {
//         axios.defaults.headers.common['Authorization'] = null;
//     }
// }

axios.interceptors.response.use(resp => resp, async error => {

    if (error.response.status === 401 && !refresh) {
        refresh = true;

        const response = await axios.post('http://localhost:8080/auth/refreshToken', {},
     {
                headers: {
                    "Authorization": `Bearer ${sessionStorage.getItem("refreshToken")}`
                }
            });
        alert("axios -> refreshToken POST with refreshToken")

        if (response.status === 200) {

            sessionStorage.setItem("token", response.data['accessToken'])
            sessionStorage.setItem("refreshToken", response.data['refreshToken'])

            return axios(error.config);
        }
    }
    refresh = false;
    return error;
});


