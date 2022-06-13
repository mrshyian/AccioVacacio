import React, {useState} from 'react';
import Button from "./button/Button";
import './Header.css'
import LoginModal from "../modals/loginModal/LoginModal";
import RegistrationModal from "../modals/registrationModal/RegistrationModal";
import ErrorModal from "../modals/errorModals/ErrorModal";
import NightModeTogle from "./togleNightMode/NightModeTogle";
import ReactDOM from 'react-dom/client';
import SearchBox from "../searchBox/SearchBox";
import MainPage from "../mainPage/MainPage";
import UserPage from "../userPage/UserPage";


const Header = (inSession) => {

    const root = ReactDOM.createRoot(document.getElementById('root'));

    const [loginModalOpen, setLoginModalOpen] = useState(false);
    const [registrationModalOpen, setRegistrationModalOpen] = useState(false);
    const [errorModalOpen, setErrorModalOpen] = useState(false);

    const renderToMyProfilePage = () => {
        root.render(
            <React.StrictMode>
                <UserPage/>
            </React.StrictMode>
        );
    }

    const renderToForumPage = () => {
        root.render(
            <React.StrictMode>
                <div className="App">
                    <Header inSession={false}/>

                </div>
            </React.StrictMode>
        );
    }

    const renderToMainPage = () => {
        root.render(
            <React.StrictMode>
                <div className="App">
                    <Header inSession={false}/>
                    <SearchBox/>
                </div>
            </React.StrictMode>
        );
    }

    return (
        <div className="header-box">
            <span className="left-buttons">
                <Button onClick={renderToMyProfilePage}>My profile</Button>
                <Button onClick={renderToForumPage}>Forum</Button>
                <Button onClick={renderToMainPage}>Main page</Button>
            </span>

            <NightModeTogle/>

            {inSession===true ? <span className="right-buttons"><Button>Logout</Button></span> :
                <span className="right-buttons">
                    <button className="noselect" onClick={() => {setLoginModalOpen(true);}}>Log in</button>
                    <button className="noselect" onClick={() => {setRegistrationModalOpen(true);}}>Registration</button>
                </span>}
            {loginModalOpen && <LoginModal setLoginOpenModal={setLoginModalOpen} />}
            {registrationModalOpen && <RegistrationModal setRegistrationOpenModal={setRegistrationModalOpen} />}
            {errorModalOpen && <ErrorModal setErrorModalOpen={setErrorModalOpen} error={"tekst pomylki"}/>}
        </div>
    );
};

export default Header;