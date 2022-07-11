import React, {useEffect} from 'react';
import jwt_decode from "jwt-decode";
import axios from "axios";


const GoogleSignIn = () => {
    const google = window.google

    function handleCallbackResponse(responce) {
        console.log(jwt_decode(responce.credential));

        const url = "http://localhost:8080/login_with_google";
        axios.post(url,{
            email: jwt_decode(responce.credential).email,
            fullName: jwt_decode(responce.credential).given_name + " " + jwt_decode(responce.credential).family_name,
            avatar: jwt_decode(responce.credential).picture
        })
            .then(res=>{
                console.log(res.data);
                sessionStorage.setItem("userId", res.data)
                window.location.reload();
            })
    }

    useEffect(() => {
        google.accounts.id.initialize({
            client_id: "15327293556-19hqs1au1h2arpvjadru4cs6k9r5rdd2.apps.googleusercontent.com",
            callback: handleCallbackResponse
        });

        google.accounts.id.renderButton(
            document.getElementById("signInDiv"),
            {
                theme: "outline",
                size: "lимпarge"
            }
        );
    }, [])

    return (
        <div style={{marginLeft: "26%"}} id="signInDiv"/>
    );
};

export default GoogleSignIn;