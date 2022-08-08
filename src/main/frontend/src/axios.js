import axios from "axios";
let refresh = false;

axios.interceptors.response.use(resp => resp, async error => {
    // alert("wchodzi tu o")
    axios.defaults.headers.common['Authorization'] = `Bearer ${sessionStorage.getItem("token")}`;

    if (error.response.status === 403) {
        // alert("403")
        return ;
    }

    if (error.response.status === 401 && !refresh) {
        // alert("401")
        refresh = true;

        const response = await axios.post('http://localhost:8080/auth/refreshToken', {},
     {
                headers: {
                    "Authorization": `Bearer ${sessionStorage.getItem("refreshToken")}`
                }
            });

        if (response.status === 200) {
            // alert("200")
            sessionStorage.setItem("token", response.data['accessToken']);
            sessionStorage.setItem("refreshToken", response.data['refreshToken']);
            return axios(error.config);
        }
    }
    refresh = false;
    return error;
});



