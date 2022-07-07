import React from 'react';
import "./SocialMedia.css"

const SocialMedia = (props) => {

    return (
        <div style={{marginTop: "80%"}}>
            <a href={props.myUser.facebook}>
                <img className="social-media-image" src="https://cdn.icon-icons.com/icons2/1099/PNG/512/1485482214-facebook_78681.png" alt="facebook"/>
            </a>
            <a href={props.myUser.instagram}>
                <img className="social-media-image" src="https://cdn.icon-icons.com/icons2/1753/PNG/512/iconfinder-social-media-applications-3instagram-4102579_113804.png" alt="instagram"/>
            </a>
        </div>


    );
};

export default SocialMedia;