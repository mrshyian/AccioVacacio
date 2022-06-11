import React, {useState} from 'react';
import "./Header.css"
import {Button, Container, Nav, Navbar} from 'react-bootstrap';
import ReactDOM from 'react-dom/client';
import UserPage from "../userPage/UserPage";
import SearchBox from "../searchBox/SearchBox";
import NightModeTogle from "./togleNightMode/NightModeTogle";

const Header = (props) => {

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
        <Navbar  bg="dark" variant="dark">
            <Container fluid>
                <Navbar.Brand  href="#">Travel Helper</Navbar.Brand>
                <Navbar.Toggle aria-controls="navbarScroll" />
                <Navbar.Collapse id="navbarScroll">
                    <Nav
                        className="me-auto my-2 my-lg-0"
                        style={{ maxHeight: '100px' }}
                        navbarScroll
                    >
                        <Button variant="outline-warning" onClick={renderToMyProfilePage}>My Profile</Button>
                        <Button variant="outline-warning" onClick={renderToForumPage} style={{marginLeft: "5px"}}>Forum</Button>
                        <Button variant="outline-warning" onClick={renderToMainPage} style={{marginLeft: "5px"}}>Search City</Button>
                    </Nav>
                    <NightModeTogle/>

                    {props.inSession===true ? <Button variant="outline-warning">Logout</Button> :
                        <span>
                    <Button variant="outline-warning" onClick={renderToMyProfilePage}>Log In</Button>
                    <Button variant="outline-warning" style={{marginLeft: "5px"}} onClick={() => {setRegistrationModalOpen(true);}}>Registration</Button>
                        </span>}

                </Navbar.Collapse>
            </Container>
        </Navbar>

    );
};

export default Header;