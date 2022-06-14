import React, {useState} from 'react';
import "./RegistrationModal.css"
import {Button, Form, Modal} from "react-bootstrap";

const RegistrationModal = ({ setRegistrationOpenModal }) => {

    const [fullName, setFullName] = useState("")
    const [nickName, setNickName] = useState("")
    const [birthday, setBirthday] = useState("")
    const [email, setEmail] = useState("")
    const [password, setPassword] = useState("")
    const [repeatPassword, setRepeatPassword] = useState("")

    const [showRegistrationModal, setShowRegistrationModal] = useState(true);
    const handleCloseRegistrationModal = () => setShowRegistrationModal(false);
    const handleShowRegistrationModal = () => setShowRegistrationModal(true);

    console.log(fullName, nickName, birthday, email, password, repeatPassword)

    return (
        <Modal show={showRegistrationModal} onHide={handleCloseRegistrationModal} style={{background: "rgba(0, 0, 0, 0.6)", color: "orange"}}>
            <Modal.Header closeButton style={{background: "rgb(40,40,40)"}}>
                <Modal.Title>Registration</Modal.Title>
            </Modal.Header>
            <Modal.Body style={{background: "rgb(20,20,20)"}}>
                <Form style={{background: "rgb(20,20,20)"}}>
                    <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                        <Form.Label style={{color: "orange"}}>Full name</Form.Label>
                        <Form.Control
                            type="text"
                            placeholder="Full name"
                            value={fullName}
                            onChange={e=> setFullName(e.target.value)}
                        />
                    </Form.Group>

                    <Form.Group
                        className="mb-3"
                        controlId="exampleForm.ControlTextarea1"
                    >
                        <Form.Label style={{color: "orange"}}>Nickname</Form.Label>
                        <Form.Control
                            type="text"
                            placeholder="Nickname"
                            value={nickName}
                            onChange={e=> setNickName(e.target.value)}/>
                    </Form.Group>

                    <Form.Group
                        className="mb-3"
                        controlId="exampleForm.ControlTextarea1"
                    >
                        <Form.Label style={{color: "orange"}}>Birthday</Form.Label>
                        <Form.Control
                            type="date"
                            placeholder="Birthday"
                            value={birthday}
                            onChange={e=> setBirthday(e.target.value)}/>
                    </Form.Group>

                    <Form.Group
                        className="mb-3"
                        controlId="exampleForm.ControlTextarea1"
                    >
                        <Form.Label style={{color: "orange"}}>E-mail</Form.Label>
                        <Form.Control
                            type="email"
                            placeholder="name@example.com"
                            value={email}
                            onChange={e=> setEmail(e.target.value)}/>
                    </Form.Group>

                    <Form.Group
                        className="mb-3"
                        controlId="exampleForm.ControlTextarea1"
                    >
                        <Form.Label style={{color: "orange"}}>Password</Form.Label>
                        <Form.Control
                            type="password"
                            placeholder="********"
                            value={password}
                            onChange={e=> setPassword(e.target.value)}/>
                    </Form.Group>

                    <Form.Group
                        className="mb-3"
                        controlId="exampleForm.ControlTextarea1"
                    >
                        <Form.Label style={{color: "orange"}}>Repeat password</Form.Label>
                        <Form.Control
                            type="password"
                            placeholder="********"
                            value={repeatPassword}
                            onChange={e=> setRepeatPassword(e.target.value)}/>
                    </Form.Group>
                </Form>
            </Modal.Body>
            <Modal.Footer  style={{background: "rgb(40,40,40)"}}>
                <Button variant="outline-secondary" onClick={handleCloseRegistrationModal}>
                    Close
                </Button>
                <Button variant="outline-warning" onClick={handleCloseRegistrationModal}>
                    Save Changes
                </Button>
            </Modal.Footer>
        </Modal>
    );
};

export default RegistrationModal;