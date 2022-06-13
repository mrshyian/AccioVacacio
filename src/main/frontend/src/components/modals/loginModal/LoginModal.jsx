import React, {useState} from 'react';
import "./LoginModal.css"
import {Button, Card, Modal, Form} from "react-bootstrap";

const LoginModal = (props) => {

    const [email, setEmail] = useState("")
    const [password, setPassword] = useState("")

    const [showLoginModal, setShowLoginModal] = useState(true);
    const handleCloseLoginModal = () => setShowLoginModal(false);
    const handleShowLoginModal = () => setShowLoginModal(true);


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
        // <Modal.Dialog>
        //     <Modal.Header style={{background: "rgb(40,40,40)"}} closeButton variant={"warning"} onClick={() => {setLoginOpenModal(false);}}>
        //         <Modal.Title>Log in</Modal.Title>
        //     </Modal.Header>
        //     <Modal.Body style={{background: "rgb(20,20,20)"}}>
        //         <Card.Text>
        //             <input className="myInput" type="country" placeholder="E-mail" value={email} onChange={e=> setEmail(e.target.value)}/>
        //             <input className="myInput" type="password" placeholder="Password" value={password} onChange={e=> setPassword(e.target.value)}/>
        //         </Card.Text>
        //     </Modal.Body>
        //     <Modal.Footer style={{background: "rgb(40,40,40)"}}>
        //         <Button variant={"warning"} onClick={() => {setLoginOpenModal(false);}}>Cancel</Button>
        //         <Button variant={"warning"} >Continue</Button>
        //     </Modal.Footer>
        // </Modal.Dialog>
    );
};

export default LoginModal;