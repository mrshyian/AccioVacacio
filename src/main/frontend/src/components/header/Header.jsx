import React from 'react';
import Button from "../button/Button";
import './Header.css'

const Header = (inSession) => {

    const renderToMyProfilePage = () => {
    //TODO: methode to render to "My profile" page
    }

    const renderToForumPage = () => {
    //TODO: methode to render to "Forum" page
    }

    const renderToMainPage = () => {
    //TODO: methode to render to "Main page" page
    }

    return (
        <div className="header-box">
            <span className="left-buttons">
                <Button onClick={renderToMyProfilePage}>My profile</Button>
                <Button onClick={renderToForumPage}>Forum</Button>
                <Button onClick={renderToMainPage}>Main page</Button>
            </span>
            {inSession===true ? <span className="right-buttons"><Button>Logout</Button></span> : <span className="right-buttons"><Button>Login</Button> <Button>Registration</Button></span>}
        </div>
    );
};

export default Header;