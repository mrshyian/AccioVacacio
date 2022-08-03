import axios from "axios";

let refresh = false;

axios.interceptors.response.use(resp => resp, async error => {

    alert("weszło do axios.js")
    if (error.response.status === 401 && !refresh) {
        refresh = true;
        alert("jestem w ifie")
        alert(sessionStorage.getItem("token"))
        const response = await axios.post('http://localhost:8080/auth/refreshToken', {}, {
            headers: {
                    "Authorization": `Bearer ${sessionStorage.getItem("token")}`}});
        console.log(sessionStorage.getItem("token"))
        console.log(response)
        console.log(response.headers)
        if (response.status === 200) {
            axios.defaults.headers.common['Authorization'] = `Bearer ${response.data['token']}`;
            console.log("to jest error")
            return axios(error.config);
        }
    }
    refresh = false;
    return error;
});


