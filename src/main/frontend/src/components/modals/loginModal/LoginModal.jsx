import React, {useState} from 'react';
import "./LoginModal.css"
import {Button, Card, Modal, Form} from "react-bootstrap";
import axios from "axios";


const LoginModal = (props) => {

    const [email, setEmail] = useState("")
    const [password, setPassword] = useState("")

    const [showLoginModal, setShowLoginModal] = useState(true);
    const handleCloseLoginModal = () => setShowLoginModal(false);
    const handleShowLoginModal = () => setShowLoginModal(true);


    const getUserIdFromSession = () => {
        if (localStorage.getItem("userId") !== null){
            props.setsession(true)
        }
    }


    const sendDataToServer = () => {
            const url = "http://localhost:8080/login";
            axios.post(url,{
                email: email,
                password: password,
            })
                .then(()=>{
                    fetchUserId();
                })
            handleCloseLoginModal()
    }

    const fetchUserId = () => {
        axios.get("http://localhost:8080/login")
            .then(res=> {
                localStorage.setItem('userId', res.data)
            })
        .catch(err => {console.log(err)});
        getUserIdFromSession();
    }

    return (
        <Modal show={showLoginModal} onHide={handleCloseLoginModal} style={{background: "rgba(0, 0, 0, 0.6)", color: "orange"}}>
            <Modal.Header closeButton style={{background: "rgb(40,40,40)"}}>
                <Modal.Title>Log In</Modal.Title>
            </Modal.Header>
            <Modal.Body style={{background: "rgb(20,20,20)"}}>
                <Form style={{background: "rgb(20,20,20)"}}>
                    <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                        <Form.Label style={{color: "orange"}}>Email address</Form.Label>
                        <Form.Control
                            value={email}
                            onChange={e=> setEmail(e.target.value)}
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
                            value={password}
                            onChange={e=> setPassword(e.target.value)}
                            type="password"
                            placeholder="*******"/>
                    </Form.Group>
                </Form>
            </Modal.Body>
            <Modal.Footer  style={{background: "rgb(40,40,40)"}}>
                <Button variant="outline-secondary" onClick={handleCloseLoginModal}>
                    Close
                </Button>
                <Button variant="outline-warning" onClick={sendDataToServer}>
                    Save Changes
                </Button>
            </Modal.Footer>
        </Modal>
    );
};

export default LoginModal;