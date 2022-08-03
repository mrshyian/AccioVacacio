import axios from "axios";

let refresh = false;

axios.interceptors.response.use(resp => resp, async error => {

    // alert("weszło do axios.js")
    if (error.response.status === 401 && !refresh) {
        refresh = true;
        // alert("jestem w ifie")
        // alert(sessionStorage.getItem("token"))
        const response = await axios.post('http://localhost:8080/auth/refreshToken', {}, {
            headers: {
                    "Authorization": `Bearer ${sessionStorage.getItem("refreshToken")}`}});
        console.log("Token "+ sessionStorage.getItem("token"))
        console.log("refreshToken "+sessionStorage.getItem("refreshToken"))
        // console.log(sessionStorage.getItem("token"))
        // console.log(response)
        // console.log(response.headers)
        if (response.status === 200) {
            // axios.defaults.headers.common['Authorization'] = `Bearer ${response.data['token']}`;
            // sessionStorage.setItem()
            // function parseJwt(token) {
            //     if (!token) { return; }
            //     const base64Url = token.split('.')[1];
            //     const base64 = base64Url.replace('-', '+').replace('_', '/');
            //     return JSON.parse(window.atob(base64));
            // }

            // sessionStorage.removeItem("userId")
            console.log(response.data)
            sessionStorage.removeItem("token")
            sessionStorage.removeItem("refreshToken")
            // sessionStorage.setItem("userId", parseJwt(response.data['tokenDostempowy']).sub)
            sessionStorage.setItem("token", response.data['tokenDostempowy'])
            sessionStorage.setItem("refreshToken", response.data['refreshToken'])
            console.log("weszło do 200")
            return axios(error.config);
        }
    }
    refresh = false;
    return error;
});


