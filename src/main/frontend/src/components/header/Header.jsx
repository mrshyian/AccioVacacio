import React, {useState} from 'react';
import "./Header.css"
import {Button, Container, Nav, Navbar, Modal, Form} from 'react-bootstrap';
import ReactDOM from 'react-dom/client';
import UserPage from "../userPage/UserPage";
import SearchBox from "../searchBox/SearchBox";
import AllCarousel from "../carousel/AllCarousel";
import Forum from "../forum/Forum";


const Header = (props) => {

    const root = ReactDOM.createRoot(document.getElementById('root'));

    const [showLoginModal, setShowLoginModal] = useState(false);
    const handleCloseLoginModal = () => setShowLoginModal(false);
    const handleShowLoginModal = () => setShowLoginModal(true);

    const [showRegistrationModal, setShowRegistrationModal] = useState(false);
    const handleCloseRegistrationModal = () => setShowRegistrationModal(false);
    const handleShowRegistrationModal = () => setShowRegistrationModal(true);

    const renderToMyProfilePage = () => {
        root.render(
            <React.StrictMode>
                <UserPage/>
            </React.StrictMode>
        );
    }

    const renderToMainPage = () => {
        root.render(
            <React.StrictMode>
                <Header inSession={false}/>
                <AllCarousel/>
            </React.StrictMode>
        );
    }

    const renderToForumPage = () => {
        root.render(
            <React.StrictMode>
                <Forum/>
            </React.StrictMode>
        );
    }

    const renderToSearchPage = () => {
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
        <Navbar bg="dark" variant="dark">
            <Modal show={showLoginModal} onHide={handleCloseLoginModal} style={{background: "rgba(0, 0, 0, 0.6)", color: "orange"}}>
                <Modal.Header closeButton style={{background: "rgb(40,40,40)"}}>
                    <Modal.Title>Log In</Modal.Title>
                </Modal.Header>
                <Modal.Body style={{background: "rgb(20,20,20)"}}>
                    <Form style={{background: "rgb(20,20,20)"}}>
                        <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                            <Form.Label style={{color: "orange"}}>Email address</Form.Label>
                            <Form.Control
                                type="email"
                                placeholder="name@example.com"
                                autoFocus
                            />
                        </Form.Group>
                        <Form.Group
                            className="mb-3"
                            controlId="exampleForm.ControlTextarea1"
                        >
                            <Form.Label style={{color: "orange"}}>Password</Form.Label>
                            <Form.Control
                                type="password"
                                placeholder="*******"/>
                        </Form.Group>
                    </Form>
                </Modal.Body>
                <Modal.Footer  style={{background: "rgb(40,40,40)"}}>
                    <Button variant="outline-secondary" onClick={handleCloseLoginModal}>
                        Close
                    </Button>
                    <Button variant="outline-warning" onClick={handleCloseLoginModal}>
                        Save Changes
                    </Button>
                </Modal.Footer>
            </Modal>
            <Modal show={showRegistrationModal} onHide={handleCloseRegistrationModal} style={{background: "rgba(0, 0, 0, 0.6)", color: "orange"}}>
                <Modal.Header closeButton style={{background: "rgb(40,40,40)"}}>
                    <Modal.Title>Registration</Modal.Title>
                </Modal.Header>
                <Modal.Body style={{background: "rgb(20,20,20)"}}>
                    <Form style={{background: "rgb(20,20,20)"}}>
                        <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                            <Form.Label style={{color: "orange"}}>Email address</Form.Label>
                            <Form.Control
                                type="email"
                                placeholder="name@example.com"
                                autoFocus
                            />
                        </Form.Group>
                        <Form.Group
                            className="mb-3"
                            controlId="exampleForm.ControlTextarea1"
                        >
                            <Form.Label style={{color: "orange"}}>Password</Form.Label>
                            <Form.Control
                                type="password"
                                placeholder="*******"/>
                        </Form.Group>
                    </Form>
                </Modal.Body>
                <Modal.Footer  style={{background: "rgb(40,40,40)"}}>
                    <Button variant="outline-secondary" onClick={handleCloseLoginModal}>
                        Close
                    </Button>
                    <Button variant="outline-warning" onClick={handleCloseLoginModal}>
                        Save Changes
                    </Button>
                </Modal.Footer>
            </Modal>
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
                    <Button variant="outline-warning" onClick={handleShowLoginModal}>Log In</Button>
                    <Button variant="outline-warning" style={{marginLeft: "5px"}} onClick={handleShowRegistrationModal}>Registration</Button>
                        </span>}

                </Navbar.Collapse>
            </Container>
        </Navbar>

    );
};

export default Header;