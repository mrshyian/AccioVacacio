import React, {useEffect, useState} from 'react';
import "./Chat.css"
import {Button, FormControl, InputGroup} from "react-bootstrap";
import {getResponseFromAxiosGet, postDataToServerByAxiosPost} from "../../../../../axios";


const Chat = (props) => {
    const [messages, setMessages] = useState([]);
    const chatUrl = `http://localhost:8080/mail_to_friend/messages/${props.friend.id}`;
    const newMessageUrl = `http://localhost:8080/mail_to_friend/new_message_in_chat`;


    const getMessages = () => {
        getResponseFromAxiosGet(chatUrl, 2).then(resp => setMessages(resp.data));
    };

    useEffect(() => {
        (async () => getMessages())()
    }, [])

    useEffect(() => {
        (async () => getMessages())()
    }, [props])

    const [messageText, setMessageText] = useState("")

    const sendMessageInChat =  () => {
        const data = {
            messageText: messageText,
            toUserId: props.friend.id
        };
        postDataToServerByAxiosPost(newMessageUrl, data, 0).then(() => refreshMessage());
    }

    const refreshMessage = () => {
        setMessageText("");
        getResponseFromAxiosGet(chatUrl, 2).then(resp => setMessages(resp.data));
    }


    return (
        <div>
            <div style={{border: "4px solid orange", height: "500px", marginLeft: "20px", display: "block", position: "unset", overflowY: "auto", paddingBottom: "6%"}}>
                {messages.map((message, index) => {
                    if (message.toUser.id.toString() === sessionStorage.getItem("userId")) {
                        return <div className="left-side-message" key={index}>
                            <div className="date-position-box">
                                <div className="left-side-nickname">{props.friend.nickName}</div>
                                <div className="left-message-in-chat">{message.messageText}</div>
                            </div>
                            <div className="date-position-left">{message.dateTimeOfSending}</div>
                        </div>
                    } else if (message.fromUser.id.toString() === sessionStorage.getItem("userId")) {
                        return <div className="right-side-message" key={index} id={index.toString()+"scroll"}>
                            <div className="date-position-box">
                                <div className="right-message-in-chat">{message.messageText}</div>
                                <div className="right-side-nickname">Me</div>
                            </div>
                            <div className="date-position-right">{message.dateTimeOfSending}</div>
                        </div>
                    }
                })}
                <div style={{
                    display: "flex",
                    justifyContent: "space-between",
                    bottom: 5,
                    position: "absolute",
                    width: "70%"
                }}>
                    <InputGroup style={{width: "100%", marginLeft: "5%"}}>
                        <FormControl
                            className="message-input"
                            as="input"
                            value={messageText}
                            onChange={(e) => setMessageText(e.target.value)}
                        />
                    </InputGroup>
                    <Button variant={"warning"} onClick={() => sendMessageInChat()}>Send</Button>
                </div>
            </div>
        </div>
    );
};

export default Chat;