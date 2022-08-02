import axios from "axios";

let refresh = false;

axios.interceptors.response.use(resp => resp, async error => {
    if (error.response.status === 401 && !refresh) {
        refresh = true;

        const response = await axios.post('http://localhost:8080/auth/refreshToken', {}, {withCredentials: true});

        if (response.status === 200) {
            axios.defaults.headers.common['Authorization'] = `Bearer ${response.data['token']}`;
            sessionStorage.setItem("token", response.data['token'])
            return axios(error.config);
        }
    }
    refresh = false;
    return error;
});