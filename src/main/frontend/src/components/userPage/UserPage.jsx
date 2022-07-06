import React from 'react';
import UserLeftBar from "./userLeftBar/UserLeftBar";
import UserMainBar from "./userMainBar/UserMainBar";
import MustBeLogIn from "../mustBeLogIn/MustBeLogIn";

const UserPage = () => {
    if (sessionStorage.getItem("userId") === null){
        return <MustBeLogIn/>;
    } else {
        return (
            <div>
                <UserLeftBar/>
                <UserMainBar/>
            </div>
        );
    }

};

export default UserPage;