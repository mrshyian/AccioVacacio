import React from 'react';
import "./ProfileImage.css"

const ProfileImage = () => {
    return (
        <div style={{marginLeft: "auto"}}>
            <img className="profile-image" src="https://upload.wikimedia.org/wikipedia/commons/thumb/7/79/TwinkleLab_First_Fiddle.jpg/1200px-TwinkleLab_First_Fiddle.jpg" alt="some image" />
        </div>
    );
};

export default ProfileImage;