import React from 'react';
import Header from "../header/Header";
import UserLeftBar from "./userLeftBar/UserLeftBar";
import UserMainBar from "./userMainBar/UserMainBar";

const UserPage = () => {
    return (
        <div>
            <Header/>
            <UserLeftBar/>
            <UserMainBar/>
        </div>
    );
};

export default UserPage;