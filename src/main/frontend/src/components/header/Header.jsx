import React, {useState} from 'react';
import "./Header.css"
import {Button, Container, Nav, Navbar, Modal, Form} from 'react-bootstrap';
import ReactDOM from 'react-dom/client';
import UserPage from "../userPage/UserPage";
import SearchBox from "../searchCity/searchBox/SearchBox";
import AllCarousel from "../carousel/AllCarousel";
import Forum from "../forum/Forum";
import LoginModal from "../modals/loginModal/LoginModal";
import RegistrationModal from "../modals/registrationModal/RegistrationModal";
import ErrorModal from "../modals/errorModals/ErrorModal";
import handleShowLoginModal from '../modals/loginModal/LoginModal'


const Header = (props) => {

    const root = ReactDOM.createRoot(document.getElementById('root'));

    const [loginModalOpen, setLoginModalOpen] = useState(false);
    const [registrationModalOpen, setRegistrationModalOpen] = useState(false);
    const [errorModalOpen, setErrorModalOpen] = useState(false);

    const renderToMyProfilePage = () => {
        root.render(
            <React.Profiler>
                <UserPage/>
            </React.Profiler>
        );
    }

    const renderToMainPage = () => {
        root.render(
            <React.Profiler>
                <Header inSession={false}/>
                <AllCarousel/>
            </React.Profiler>
        );
    }

    const renderToForumPage = () => {
        root.render(
            <React.Profiler>
                <Forum/>
            </React.Profiler>
        );
    }

    const renderToSearchPage = () => {
        root.render(
            <React.Profiler>
                <div className="App">
                    <Header inSession={false}/>
                    <SearchBox/>
                </div>
            </React.Profiler>
        );
    }


    return (
        <div>
        <Navbar bg="dark" variant="dark">
            <Container fluid>
                <Navbar.Brand style={{cursor: "pointer"}} onClick={renderToMainPage}>Travel Helper</Navbar.Brand>
                <Navbar.Toggle aria-controls="navbarScroll"/>
                <Navbar.Collapse id="navbarScroll">
                    <Nav
                        className="me-auto my-2 my-lg-0"
                        style={{maxHeight: '100px'}}
                        navbarScroll
                    >
                        <Button variant="outline-warning" onClick={renderToMyProfilePage}>My Profile</Button>
                        <Button variant="outline-warning" onClick={renderToForumPage}
                                style={{marginLeft: "5px"}}>Forum</Button>
                        <Button variant="outline-warning" onClick={renderToSearchPage} style={{marginLeft: "5px"}}>Search
                            City</Button>
                    </Nav>


                    {props.inSession === true ? <Button variant="outline-warning">Logout</Button> :
                        <span>
                    <Button variant="outline-warning" onClick={() => {setLoginModalOpen(true);}}>Log In</Button>
                    <Button variant="outline-warning" style={{marginLeft: "5px"}} onClick={() => {setRegistrationModalOpen(true);}}>Registration</Button>
                        </span>}

                </Navbar.Collapse>
            </Container>
        </Navbar>
            {loginModalOpen && <LoginModal open={loginModalOpen}/>}
            {registrationModalOpen && <RegistrationModal open={registrationModalOpen}/>}
            {errorModalOpen && <ErrorModal error={"tekst pomylki"}/>}
        </div>
    );
};

export default Header;