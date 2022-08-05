import axios from "axios";
import axiosRetry from "axios-retry";
let refresh = false;



axios.interceptors.response.use(resp => resp, async error => {
    alert("try")

    axios.defaults.headers.common['Authorization'] = `Bearer ${sessionStorage.getItem("token")}`;

    if (error.response.status === 403) {
        return ;
    }
    if (error.response.status === 401 && !refresh) {
        refresh = true;

        const response = await axios.post('http://localhost:8080/auth/refreshToken', {},
     {
                headers: {
                    "Authorization": `Bearer ${sessionStorage.getItem("refreshToken")}`
                }
            });

        if (response.status === 200) {
            alert("alert")
            sessionStorage.setItem("token", response.data['accessToken']);
            sessionStorage.setItem("refreshToken", response.data['refreshToken']);
            return axios(error.config);
        }
    }
    refresh = false;
    return error;
});


