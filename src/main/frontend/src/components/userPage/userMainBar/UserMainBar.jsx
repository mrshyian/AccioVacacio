import React from 'react';
import InformationAboutUser from "./userMainBarComponents/informationAboutUser/InformationAboutUser";
import ProfileImage from "./userMainBarComponents/profileImage/ProfileImage";
import SocialMedia from "./userMainBarComponents/socialMedia/SocialMedia";

const UserMainBar = () => {
    return (
        <div>
            <InformationAboutUser/>
            <ProfileImage/>
            <SocialMedia/>
        </div>
    );
};

export default UserMainBar;