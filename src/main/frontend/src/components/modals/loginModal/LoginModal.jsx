import React, {useState} from 'react';
import "./LoginModal.css"

const LoginModal = ({ setLoginOpenModal }) => {

    const [email, setEmail] = useState("")
    const [password, setPassword] = useState("")

    return (
        <div className="modalBackground">
            <div className="modalContainer">
                <div className="titleCloseBtn">
                    <button style={{width: "8%"}} onClick={() => {setLoginOpenModal(false);}}>X</button>
                </div>
                <div className="title">
                    <h2>Log in</h2>
                </div>
                <div className="body">
                    <input className="myInput" type="email" placeholder="E-mail" value={email} onChange={e=> setEmail(e.target.value)}/>
                    <input className="myInput" type="password" placeholder="Password" value={password} onChange={e=> setPassword(e.target.value)}/>
                </div>
                <div className="footer">
                    <button onClick={() => {setLoginOpenModal(false);}} id="cancelBtn">Cancel</button>
                    <button>Continue</button>
                </div>
            </div>
        </div>
    );
};

export default LoginModal;