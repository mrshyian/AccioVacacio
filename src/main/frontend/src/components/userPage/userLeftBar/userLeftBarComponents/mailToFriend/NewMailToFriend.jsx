import React, {useState} from 'react';
import Axios from "axios";
import UserLeftBar from "../../UserLeftBar";
import {useLocation} from "react-router-dom";
import {Button, Card, FormControl, InputGroup} from "react-bootstrap";
import "./NewMailToFriend.css"


const NewMailToFriend = () => {
    const [mailText, setMailText] = useState("")
    const [titleText, setTitleText] = useState("")

    const location = useLocation()
    const friend = location.state.friend;


    function sendMail(e) {
        e.preventDefault();
        Axios.post(`http://localhost:8080/mail_to_friend`, {
            mailText: mailText,
            mailTitle: titleText,
            friendId: friend.id
        }).then(r => {
            console.log(r.data);
        })
    }

    return (
        <div>
            <UserLeftBar/>
            <Card
                bg="dark"
                key={"dark"}
                text={'white'}
                className="mb-2 right">
                <Card.Header style={{textAlign: "center", color: "orange", display: "flex", justifyContent: "center"}}>
                    <h2 style={{marginTop: "3%"}}>Send mail to:</h2>
                    <div style={{border: "1px solid orange", marginLeft: "15px", display: "flex", justifyContent: "center", padding: "20px", background: "rgba(44,44,44,0.62)"}}>
                        <img style={{width: 50}} src={`http://localhost:8080/image/download/user/${friend.id}`}
                             alt="some image"/>
                        <div>
                            <h4 style={{marginLeft: 20}}>{friend.fullName}</h4>
                            ({friend.nickName})
                        </div>

                    </div>
                </Card.Header>
                <Card.Body>
                    <Card.Text>
                        <Card
                            bg="dark"
                            key={"dark"}>
                            <Card.Header style={{display: "flex"}}>
                                <div style={{display: "flex", width: "50%"}}>
                                    <h4 style={{color: "orange"}}>Title:</h4>
                                <InputGroup style={{marginLeft: "20px"}}>
                                    <FormControl
                                        className="mail-header-input"
                                        as="input"
                                        value={titleText}
                                        onChange={(e) => setTitleText(e.target.value)}
                                    />
                                </InputGroup>
                                </div>
                                <Button variant="warning" style={{marginLeft: "auto"}} onClick={(e) => sendMail(e)}>Send</Button>
                            </Card.Header>
                        </Card>
                        <InputGroup>
                            <FormControl
                                className="mail-input"
                                as="textarea"
                                value={mailText}
                                onChange={(e) => setMailText(e.target.value)}
                            />
                        </InputGroup>
                    </Card.Text>
                </Card.Body>
            </Card>
        </div>
    )
};

export default NewMailToFriend;