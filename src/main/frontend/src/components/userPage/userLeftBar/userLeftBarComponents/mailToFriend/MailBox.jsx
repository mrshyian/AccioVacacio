import React, {useEffect, useState} from 'react';
import UserLeftBar from "../../UserLeftBar";
import {Button, Card} from "react-bootstrap";
import axios from "axios";
import Chat from "./Chat";

const MailBox = () => {
    const [penFriends, setPenFriends] = useState([]);

    const [showChat, setShowChat] = useState({});

    const getAllPenFriends = () => {
        axios.get(`http://localhost:8080/mail_to_friend/all_chats`)
            .then(res => {
                console.log(res.data)
                setPenFriends(res.data);
            })
            .catch(err => {
                console.log(err)
            });
    };

    useEffect( () => {
        (async () => getAllPenFriends())()
    }, [])

    return (
        <div>
            <UserLeftBar/>
            <Card
                bg="dark"
                text={'white'}
                className="mb-2 right">
                <Card.Header style={{textAlign: "center", color: "orange"}}>
                    <h3>CHAT</h3>
                </Card.Header>
                <Card
                    bg="dark"
                    style={{marginTop: 10, height: "100%"}}>
                    <Card.Body style={{display: "flex", justifyContent: "space-between"}} id="friends-list">
                        <div>
                            {penFriends.map((friend, index) => {
                                if (friend.id.toString() !== sessionStorage.getItem("userId")){
                                    return <h5 key={index}>
                                        <Button style={{width: 200}} variant={"warning"} onClick={() => {setShowChat(friend)}}>
                                            {friend.nickName}
                                        </Button>
                                    </h5>
                                }})}
                        </div>
                        <Card.Text id="chat" style={{textAlign: "center", width: "100%"}}>
                            {!showChat.nickName ?
                                <div>chat with friends</div>
                                :
                                <Chat friend={showChat}/>
                            }
                        </Card.Text>
                    </Card.Body>
                </Card>
            </Card>
        </div>
    );
};

export default MailBox;