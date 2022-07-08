import React, {useEffect} from 'react';


const GoogleSignIn = () => {
    const google = window.google

    function handleCallbackResponse(responce) {
        console.log("Encoded JWT ID token: " + responce.credential);
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
                size: "large"
            }
        );
    }, [])

    return (
        <div style={{marginLeft: "26%"}} id="signInDiv"/>
    );
};

export default GoogleSignIn;