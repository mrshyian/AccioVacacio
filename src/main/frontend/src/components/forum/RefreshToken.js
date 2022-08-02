import axios from "axios";

let refresh = false;

axios.interceptors.response.use(resp => resp, async error => {
    alert("console.log(\"weszło do refresh\")")
    if (error.response.status === 401 && !refresh) {
        refresh = true;

        const response = await axios.post('http://localhost:8080/auth/refreshToken', {}, {withCredentials: true,
            headers: {
                "Content-Type": "multipart/form-data",
                    "Authorization": `Bearer ${sessionStorage.getItem("token")}`}});

        if (response.status === 200) {
            axios.defaults.headers.common['Authorization'] = `Bearer ${response.data['token']}`;

            return axios(error.config);
        }
    }
    refresh = false;
    return error;
});


