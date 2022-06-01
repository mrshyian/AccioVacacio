import React from 'react';
import Button from "../button/Button";
import './Header.css'

const Header = (inSession) => {
    return (
        <div className="header-box">
            <span className="left-buttons">
                <Button>My profile</Button>
                <Button>Forum</Button>
                <Button>Main page</Button>
            </span>
            {inSession===true ? <span className="right-buttons"><Button>Logout</Button></span> : <span className="right-buttons"><Button>Login</Button> <Button>Registration</Button></span>}
        </div>
    );
};

export default Header;