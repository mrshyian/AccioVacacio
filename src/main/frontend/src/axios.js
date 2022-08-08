import axios from "axios";
let refresh = false;



axios.interceptors.response.use(resp => resp, async error => {
    // alert("axios")
    axios.defaults.headers.common['Authorization'] = `Bearer ${sessionStorage.getItem("token")}`;

    if (error.response.status === 403) {
        return ;
    }

    if (error.response.status === 401 && !refresh) {
        refresh = true;

        // alert(401)
        const response = await axios.post('http://localhost:8080/auth/refreshToken', {},
     {
                headers: {
                    "Authorization": `Bearer ${sessionStorage.getItem("refreshToken")}`
                }
            });

        if (response.status === 200) {
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

export async function postDataToServerByAxiosPost(url, data, repeatTimes){
    alert("postData")
    let resp = {};
    let i = 0;

    do {
        resp = await axios.post(url, data);
        i++;
    } while (i < repeatTimes || resp.status !== 200)

    alert("You've been sent data to server");
}

export async function putDataToServerByAxiosPut(url, data, repeatTimes){
    let resp = {};
    let i = 0;

    do {
        resp = await axios.put(url, data);
        i++;
    } while (i < repeatTimes || resp.status !== 200)

    alert("You've been sent data to server");
}

