import axios from "axios";
let refresh = false;



axios.interceptors.response.use(resp => resp, async error => {
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

export async function getResponseFromAxiosGet(url, repeatTimes){
    let resp = {};
    let i = 0;

    do {
        resp = await axios.get(url);
        i++;
    } while (i < repeatTimes || resp.status !== 200)

    return resp;
}

