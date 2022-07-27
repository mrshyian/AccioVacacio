import React from 'react';
import UserLeftBar from "./userLeftBar/UserLeftBar";
import UserMainBar from "./userMainBar/UserMainBar";



const UserPage = () => {
    console.log("To jest na pewno to"+ sessionStorage.getItem("token"))
    if (sessionStorage.getItem('token')!== null){
        return (
            <div>

                <UserLeftBar/>
                <UserMainBar/>
            </div>
        );
    } else {
        console.log("SAASSASAAS")
    }

};

export default UserPage;