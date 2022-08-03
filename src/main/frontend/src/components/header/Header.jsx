import React, {useState} from 'react';
import './Header.css';
import {Button, Container, Nav, Navbar} from 'react-bootstrap';
import LoginModal from '../modals/loginModal/LoginModal';
import RegistrationModal from '../modals/registrationModal/RegistrationModal';
import {Link, useNavigate} from "react-router-dom";
import {FaEnvelope} from "react-icons/fa";


const Header = () => {
    const navigate = useNavigate();
    const [loginModalOpen, setLoginModalOpen] = useState(false);
    const [registrationModalOpen, setRegistrationModalOpen] = useState(false);


    const clearSession =()=>{
        sessionStorage.clear();
    }



    return (
        <div id="top" >
            <Navbar bg="dark" variant="dark">
                <Container fluid>
                    <Link style={{textDecoration: "none"}} to="/">
                    <Navbar.Brand
                        style={{ cursor: 'pointer' }}
                    >
                        Accio Vacacio
                    </Navbar.Brand>
                    </Link>
                    <Navbar.Toggle aria-controls="navbarScroll"/>
                    <Navbar.Collapse id="navbarScroll">
                        <Nav className="me-auto my-2 my-lg-0" style={{ maxHeight: '100px' }} navbarScroll>
                            {sessionStorage.getItem("userId") !== null ?
                            <Link to="/userpage">
                                <Button
                                    variant="outline-warning"
                                    style={{marginLeft: '5px'}}
                                >
                                    My Profile
                                </Button>
                            </Link>
                                :
                                <div/>
                            }
                            <Link to="/forum">
                                <Button
                                    variant="outline-warning"
                                    style={{marginLeft: '5px'}}
                                >
                                    Forum
                                </Button>
                            </Link>
                            <Link to="/SearchBox">
                                <Button
                                    variant="outline-warning"
                                    style={{marginLeft: '5px'}}
                                >
                                    Search City
                                </Button>
                            </Link>
                        </Nav>

                        {sessionStorage.getItem("userId") !== null ? (
                            <span>
                                <Button onClick={() => {navigate("/userpage/friends/mail_box")}} variant={"warning"}>{<FaEnvelope/>}</Button>
                                <Link style={{marginLeft: 20}} to="/"><Button onClick={clearSession} variant="outline-warning">Logout</Button></Link>
                            </span>
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
                                    style={{marginLeft: '5px'}}
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
            {loginModalOpen && <LoginModal close={setLoginModalOpen}/>}
            {registrationModalOpen && <RegistrationModal close={setRegistrationModalOpen}/>}
        </div>
    );
};
export default Header;
