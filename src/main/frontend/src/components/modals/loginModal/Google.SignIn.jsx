import React, {useEffect} from 'react';
import jwt_decode from "jwt-decode";
import axios from "axios";


const GoogleSignIn = () => {
    function parseJwt(token) {
        if (!token) { return; }
        const base64Url = token.split('.')[1];
        const base64 = base64Url.replace('-', '+').replace('_', '/');
        return JSON.parse(window.atob(base64));
    }

    const google = window.google

    function handleCallbackResponse(responce) {
        // console.log(jwt_decode(responce.credential));

        const url = "http://localhost:8080/login_with_google";
        axios.post(url,{
            email: jwt_decode(responce.credential).email,
            fullName: jwt_decode(responce.credential).given_name + " " + jwt_decode(responce.credential).family_name,
            avatar: jwt_decode(responce.credential).picture
        })
            .then(res=>{
                // console.log(parseJwt(res['accessToken']))
                // sessionStorage.setItem("userId", parseJwt(res['accessToken']).sub)
                sessionStorage.setItem("token", res['accessToken'])
                console.log(res.data);

                // window.location.reload();
            })
    }

    useEffect(() => {
        google.accounts.id.initialize({
            client_id: process.env.REACT_APP_CLIENT_ID_FOR_GOOGLE_SIGN_IN,
            callback: handleCallbackResponse
        });

        google.accounts.id.renderButton(
            document.getElementById("signInDiv"),
            {
                theme: "outline",
                size: "large"
            }
        );
    }, [])

    return (
        <div style={{marginLeft: "26%"}} id="signInDiv"/>
    );
};

export default GoogleSignIn;