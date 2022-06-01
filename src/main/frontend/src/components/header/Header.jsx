import React, {useState} from 'react';
import Button from "../button/Button";
import './Header.css'
import LoginModal from "../modals/loginModal/LoginModal";
import RegistrationModal from "../modals/registrationModal/RegistrationModal";
import ErrorModal from "../modals/errorModals/ErrorModal";


const Header = (inSession) => {

    const [loginModalOpen, setLoginModalOpen] = useState(false);
    const [registrationModalOpen, setRegistrationModalOpen] = useState(false);
    const [errorModalOpen, setErrorModalOpen] = useState(false);

    const renderToMyProfilePage = () => {
    //TODO: methode to render to "My profile" page
    }

    const renderToForumPage = () => {
        setErrorModalOpen(true)
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
                    <button className="noselect" onClick={() => {setLoginModalOpen(true);}}>Log in</button>
                    <button className="noselect" onClick={() => {setRegistrationModalOpen(true);}}>Registration</button>
                </span>}
            {loginModalOpen && <LoginModal setLoginOpenModal={setLoginModalOpen} />}
            {registrationModalOpen && <RegistrationModal setRegistrationOpenModal={setRegistrationModalOpen} />}
            {errorModalOpen && <ErrorModal setErrorModalOpen={setErrorModalOpen} error={"no nie ma jeszcze tego"}/>}
        </div>
    );
};

export default Header;