import React, {useState} from 'react';
import Button from "../button/Button";
import './Header.css'
import LoginModal from "../modals/loginModal/LoginModal";
import RegistrationModal from "../modals/registrationModal/RegistrationModal";


const Header = (inSession) => {

    const [loginModalOpen, setLoginModalOpen] = useState(false);
    const [registrationModalOpen, setRegistrationModalOpen] = useState(false);

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
            {inSession===true ? <span className="right-buttons"><Button>Logout</Button></span> :
                <span className="right-buttons">
                    <button className="openModalBtn noselect" onClick={() => {setLoginModalOpen(true);}}>Log in</button>
                    <button className="openModalBtn noselect" onClick={() => {setRegistrationModalOpen(true);}}>Registration</button>
                </span>}
            {loginModalOpen && <LoginModal setLoginOpenModal={setLoginModalOpen} />}
            {registrationModalOpen && <RegistrationModal setRegistrationOpenModal={setRegistrationModalOpen} />}
        </div>
    );
};

export default Header;