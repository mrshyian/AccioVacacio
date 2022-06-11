import React, {useState} from 'react';
import "./LoginModal.css"
import { Button, Form, Modal } from 'react-bootstrap';
import {render} from "react-dom";

const LoginModal = (showProps) => {
    const [email, setEmail] = useState("")
    const [password, setPassword] = useState("")

    function OpenLoginModal() {
        const [show, setShow] = useState(false);

        const handleClose = () => setShow(false);
        const handleShow = () => setShow(true);

        return (
            <>
                <Modal show={show} onHide={handleClose}>
                    <Modal.Header closeButton>
                        <Modal.Title>Log In</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <Form>
                            <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                                <Form.Label>Email address</Form.Label>
                                <Form.Control
                                    value={email} onChange={e=> setEmail(e.target.value)}
                                    type="email"
                                    placeholder="name@example.com"
                                    autoFocus
                                />
                            </Form.Group>
                            <Form.Group
                                className="mb-3"
                                controlId="exampleForm.ControlInput2"
                            >
                                <Form.Label>Password</Form.Label>
                                <Form.Control
                                    value={password} onChange={e=> setPassword(e.target.value)}
                                    type="password"
                                    placeholder="********"
                                />
                            </Form.Group>
                        </Form>
                    </Modal.Body>
                    <Modal.Footer>
                        <Button variant="secondary" onClick={handleClose}>
                            Close
                        </Button>
                        <Button variant="primary" onClick={handleClose}>
                            Save Changes
                        </Button>
                    </Modal.Footer>
                </Modal>
            </>
        );
    }
    render(<OpenLoginModal />);
};

export default LoginModal;