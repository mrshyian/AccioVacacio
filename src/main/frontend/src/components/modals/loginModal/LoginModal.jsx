import "./LoginModal.css";
import {Button, Modal, Form} from "react-bootstrap";
import axios from "axios";
import React, {useState} from "react";
import ErrorModal from "../errorModals/ErrorModal";
import ReCAPTCHA from "react-google-recaptcha";
import GoogleSignIn from "./Google.SignIn";


const LoginModal = (props) => {

    const [disabledBtn, setDisabledBtn] = useState(false)

    const [email, setEmail] = useState("")
    const [password, setPassword] = useState("")
    const [navigate, setNavigate] = useState(false);

    const [showLoginModal, setShowLoginModal] = useState(true);
    const handleCloseLoginModal = () => {
        setToPropsModalClose();
        setShowLoginModal(false);
    };
    const [errorModalOpen, setErrorModalOpen] = useState(false);
    const [errorText, setErrorText] = useState("");


    function showErrorModal(data) {
        setErrorText(data);
        setErrorModalOpen(true);
    }


    function parseJwt(token) {
        if (!token) { return; }
        const base64Url = token.split('.')[1];
        const base64 = base64Url.replace('-', '+').replace('_', '/');
        return JSON.parse(window.atob(base64));
    }

    const sendDataToServer = () => {
        const url = `http://localhost:8080/app/login?userEmail=${email}&password=${password}`;
        fetch(url,{method:"GET"})
            .then(res=>res.json())
            .then(data=>{
                console.log(data)
                if (data.error === "niedziała"){
                    showErrorModal("Provided data is not valid")
                } else {
                    console.log("setuje tokeny")
                    sessionStorage.setItem("userId", parseJwt(data['tokenDostempowy']).sub)
                    sessionStorage.setItem("token", data['tokenDostempowy'])
                    sessionStorage.setItem("refreshToken", data['refreshToken'])
                    console.log(sessionStorage.getItem("token"))
                    console.log(sessionStorage.getItem("refreshToken"))
                    setShowLoginModal(false);
                    window.location.reload();
                }

            })
            .catch(console.error)
    }

    const submit = async e => {
        e.preventDefault();
        const {data} = await axios.post('http://localhost:8080/app/login', {
            email, password
        }, {withCredentials: true})
            .then(res => {
                console.log(res)
                if (res.error === "niedziała") {
                    showErrorModal("Provided data is not valid")
                } else {
                    axios.defaults.headers.common['Authorization'] = `Bearer ${res.data['accessToken']}`;
                    sessionStorage.setItem("userId", parseJwt(res.data['accessToken']).sub);
                    sessionStorage.setItem("token", res.data['accessToken']);
                    sessionStorage.setItem("refreshToken", res.data['refreshToken']);
                    setShowLoginModal(false);
                    window.location.reload();
                }

            });

        setNavigate(true);
    }
    return (
        <Modal show={showLoginModal} onHide={handleCloseLoginModal}
               style={{background: "rgba(0, 0, 0, 0.6)", color: "orange"}}>
            <Modal.Header closeButton style={{background: "rgb(40,40,40)"}}>
                <Modal.Title>Log In</Modal.Title>
            </Modal.Header>
            <Modal.Body style={{background: "rgb(20,20,20)"}}>
                <div style={{display: "block", textAlign: "center", justifyContent: "center"}}>
                    <GoogleSignIn/>
                    <h5 style={{margin: 10}}>Or type manually:</h5>
                </div>
                <Form style={{background: "rgb(20,20,20)"}}>
                    <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                        <Form.Label style={{color: "orange"}}>Email address</Form.Label>
                        <Form.Control
                            value={email}
                            onChange={e => setEmail(e.target.value)}
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
                            onChange={e => setPassword(e.target.value)}
                            type="password"
                            placeholder="*******"/>
                    </Form.Group>
                </Form>
            </Modal.Body>
            <Modal.Footer style={{background: "rgb(40,40,40)"}}>
                {/*<ReCAPTCHA*/}
                {/*    size="normal"*/}
                {/*    sitekey={process.env.REACT_APP_RECAPTCHA_API_KEY}*/}
                {/*    onChange={() => setDisabledBtn(false)}*/}
                {/*/>*/}
                <Button variant="outline-secondary" onClick={handleCloseLoginModal}>
                    Close
                </Button>
                {/*<Button disabled={disabledBtn} variant="outline-warning" onClick={submit}>*/}
                <Button disabled={disabledBtn} variant="outline-warning" onClick={sendDataToServer}>
                    Login
                </Button>
            </Modal.Footer>
            {errorModalOpen && <ErrorModal errorText={errorText} visible={errorModalOpen} close={setErrorModalOpen}/>}
        </Modal>
    );
};

export default LoginModal;