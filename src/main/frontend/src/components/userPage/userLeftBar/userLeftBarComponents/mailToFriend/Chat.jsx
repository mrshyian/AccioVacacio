import React, {useEffect, useState} from 'react';
import "./Chat.css"
import {Button, FormControl, InputGroup} from "react-bootstrap";
import axios from "axios";


const Chat = (props) => {
    const [messages, setMessages] = useState([]);

    const getMessages = () => {
        axios.get(`http://localhost:8080/mail_to_friend/messages/${props.friend.id}`)
            .then(res => {
                setMessages(res.data);
            })
            .catch(err => {
                console.log(err)
            });
    };

    useEffect(() => {
        getMessages();
    }, [])

    useEffect(() => {
        getMessages();
    }, [props])

    const [messageText, setMessageText] = useState("")


    return (
        <div>
            <div style={{border: "4px solid orange", height: "500px", marginLeft: "20px", display: "block"}}>
                {messages.map((message, index) => {
                    console.log(message)
                    if (message.toUser.id.toString() === sessionStorage.getItem("userId")) {
                        return <div className="left-side-message" key={index}>
                            <div className="left-side-nickname">{props.friend.nickName}</div>
                            <div className="message-in-chat">{message.messageText}</div>
                        </div>
                    } else if (message.fromUser.id.toString() === sessionStorage.getItem("userId")){
                        return <div className="right-side-message" key={index}>
                            <div className="message-in-chat">{message.messageText}</div>
                            <div className="right-side-nickname">Me</div>
                        </div>
                    }
                })
                }
                <div style={{display: "flex", justifyContent: "space-between", bottom: 5, position: "absolute", width: "70%"}}>
                    <InputGroup style={{width: "100%", marginLeft: "5%"}}>
                        <FormControl
                            className="message-input"
                            as="input"
                            value={messageText}
                            onChange={(e) => setMessageText(e.target.value)}
                        />
                    </InputGroup>
                    <Button variant={"warning"}>Send</Button>
                </div>

            </div>
        </div>
    );
};

export default Chat;