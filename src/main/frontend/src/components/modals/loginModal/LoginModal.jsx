import "./LoginModal.css";
import {Button, Card, Modal, Form} from "react-bootstrap";
import axios from "axios";
import React, {useState} from "react";
import ErrorModal from "../errorModals/ErrorModal";
import ReCAPTCHA from "react-google-recaptcha";
import data from "bootstrap/js/src/dom/data";


const LoginModal = () => {
    const [disabledBtn, setDisabledBtn] = useState(false) // cofnij do true

    const [email, setEmail] = useState("")
    const [password, setPassword] = useState("")

    const [showLoginModal, setShowLoginModal] = useState(true);
    const handleCloseLoginModal = () => setShowLoginModal(false);
    const [errorModalOpen, setErrorModalOpen] = useState(false);
    const [errorText, setErrorText]= useState("");


    function showErrorModal(data){
        setErrorText(data);
        setErrorModalOpen(true);
    }



    // const sendDataToServer = () => {
    //         const url = "http://localhost:8080/login";
    //         axios.post(url,{
    //             email: email,
    //             password: password,
    //         })
    //             .then(res=>{
    //                 if (res.data ===""){
    //                     fetchUserId();
    //                     window.location.reload();
    //                 }else {
    //                         showErrorModal(res.data);
    //                 }
    //             })
    // }

    const sendDataToServer = () => {
        const url = `http://localhost:8080/app/login?userEmail=${email}&password=${password}`;
        fetch(url,{method:"POST"}).then(res=>res.json()).then(data=>{
            console.log(data)}).catch(console.error)
        // axios.post(url)
        //     .then(res=>{
        //         console.log("AAAAAAAAAAAAAA")
        //         if (res.data !==""){
        //
        //             // fetchUserId();
        //             window.location.reload();
        //         }else {
        //             showErrorModal(res.data);
        //         }
        //     })

    }


    const fetchUserId = () => {
        axios.get("http://localhost:8080/login")
            .then(res=> {
                if (res.data !== ""){
                    localStorage.setItem('userId', res.data)
                    sessionStorage.setItem("userId", res.data)
                }
            })
        .catch(err => {console.log(err)});
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
            <Modal.Footer  style={{background: "rgb(40,40,40)"}} >
                {/*<ReCAPTCHA*/}
                {/*    size="normal"*/}
                {/*    sitekey="6Let98ogAAAAAH3niinH0n8_di4vhssvE5YL_AuF"*/}
                {/*    onChange={() => setDisabledBtn(false)}*/}
                {/*/>*/}
                <Button variant="outline-secondary" onClick={handleCloseLoginModal}>
                    Close
                </Button>
                <Button disabled={disabledBtn} variant="outline-warning" onClick={sendDataToServer}>
                    Login
                </Button>
            </Modal.Footer>
            {errorModalOpen && <ErrorModal errorText={errorText} visible={errorModalOpen}/>}
        </Modal>
    );
};

export default LoginModal;