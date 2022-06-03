import React, {useState} from 'react';
import "./RegistrationModal.css"

const RegistrationModal = ({ setRegistrationOpenModal }) => {

    const [fullName, setFullName] = useState("")
    const [nickName, setNickName] = useState("")
    const [birthday, setBirthday] = useState("")
    const [email, setEmail] = useState("")
    const [password, setPassword] = useState("")
    const [repeatPassword, setRepeatPassword] = useState("")

    console.log(fullName, nickName, birthday, email, password, repeatPassword)

    return (
        <div className="registrationModalBackground">
            <div className="registrationModalContainer">
                <div className="registrationTitleCloseBtn">
                    <button style={{width: "8%"}} onClick={() => {setRegistrationOpenModal(false);}}>X</button>
                </div>
                <div className="registrationTitle">
                    <h1>Registration</h1>
                </div>
                <div className="registrationBody">
                    <input className="myInputForRegistration" type="text" placeholder="Full name" value={fullName} onChange={e=> setFullName(e.target.value)}/>
                    <input className="myInputForRegistration" type="text" placeholder="Nickname" value={nickName} onChange={e=> setNickName(e.target.value)}/>
                    <input className="myInputForRegistration" type="date" placeholder="Birthday" value={birthday} onChange={e=> setBirthday(e.target.value)}/>
                    <input className="myInputForRegistration" type="email" placeholder="E-mail" value={email} onChange={e=> setEmail(e.target.value)}/>
                    <input className="myInputForRegistration" type="password" placeholder="Password" value={password} onChange={e=> setPassword(e.target.value)}/>
                    <input className="myInputForRegistration" type="password" placeholder="Repeat password" value={repeatPassword} onChange={e=> setRepeatPassword(e.target.value)}/>
                </div>
                <div className="registrationFooter">
                    <button onClick={() => {setRegistrationOpenModal(false);}} id="cancelBtn">Cancel</button>
                    <button>Submit</button>
                </div>
            </div>
        </div>
    );
};

export default RegistrationModal;