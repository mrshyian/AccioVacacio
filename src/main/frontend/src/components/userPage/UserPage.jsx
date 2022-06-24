import React from 'react';
import UserLeftBar from "./userLeftBar/UserLeftBar";
import UserMainBar from "./userMainBar/UserMainBar";
import Header from "../header/Header";

const UserPage = () => {
    return (
        <div>
            <UserLeftBar/>
            <UserMainBar/>
        </div>
    );
};

export default UserPage;