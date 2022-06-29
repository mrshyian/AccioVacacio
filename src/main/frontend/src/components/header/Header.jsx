import React, {useState} from 'react';
import './Header.css';
import {Button, Container, Nav, Navbar} from 'react-bootstrap';
import LoginModal from '../modals/loginModal/LoginModal';
import RegistrationModal from '../modals/registrationModal/RegistrationModal';
import ErrorModal from '../modals/errorModals/ErrorModal';
import {Link} from "react-router-dom";


const Header = () => {
    const [loginModalOpen, setLoginModalOpen] = useState(false);
    const [registrationModalOpen, setRegistrationModalOpen] = useState(false);
    const [errorModalOpen, setErrorModalOpen] = useState(false);
    const [userIdInSession, setUserIdInSession] = useState(false);

    return (
        <div>
            <Navbar bg="dark" variant="dark">
                <Container fluid>
                    <Link style={{textDecoration: "none"}} to="/">
                    <Navbar.Brand
                        style={{ cursor: 'pointer' }}
                    >
                        Travel Helper
                    </Navbar.Brand>
                    </Link>
                    <Navbar.Toggle aria-controls="navbarScroll" />
                    <Navbar.Collapse id="navbarScroll">
                        <Nav className="me-auto my-2 my-lg-0" style={{ maxHeight: '100px' }} navbarScroll>
                            <Link to="/userpage" target="_blank">
                                <Button
                                    variant="outline-warning"
                                    style={{ marginLeft: '5px' }}
                                >
                                    My Profile
                                </Button>
                            </Link>
                            <Link to="/forum" >
                                <Button
                                    variant="outline-warning"
                                    style={{ marginLeft: '5px' }}
                                >
                                    Forum
                                </Button>
                            </Link>
                            <Link to="/SearchBox">
                                <Button
                                    variant="outline-warning"
                                    style={{ marginLeft: '5px' }}
                                >
                                    Search City
                                </Button>
                            </Link>
                        </Nav>

                        {userIdInSession === true ? (
                            <Button variant="outline-warning">Logout</Button>
                        ) : (
                            <span>
								<Button
                                    variant="outline-warning"
                                    onClick={() => {
                                        setLoginModalOpen(true);
                                    }}
                                >
									Log In
								</Button>
								<Button
                                    variant="outline-warning"
                                    style={{ marginLeft: '5px' }}
                                    onClick={() => {
                                        setRegistrationModalOpen(true);
                                    }}
                                >
									Registration
								</Button>
							</span>
                        )}
                    </Navbar.Collapse>
                </Container>
            </Navbar>
            {loginModalOpen && <LoginModal setsession={setUserIdInSession} open={loginModalOpen} />}
            {registrationModalOpen && <RegistrationModal open={registrationModalOpen} />}
            {errorModalOpen && <ErrorModal error={'tekst pomylki'} />}

        </div>
    );
};

export default Header;
