import React, {useEffect, useState} from 'react';
import UserLeftBar from "../../UserLeftBar";
import {Button, Card} from "react-bootstrap";
import axios from "axios";
import Chat from "./Chat";

const MailBox = () => {

    const openChat = () => {
      document.getElementById("chat").innerHTML = "<div dangerouslySetInnerHTML={{__html: Chat}}/>";
    }

    const [penFriends, setPenFriends] = useState([]);

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

    useEffect(() => {
        getAllPenFriends();
    }, [])

    return (
        <div>
            <UserLeftBar/>
            <Card
                bg="dark"
                key={"dark"}
                text={'white'}
                className="mb-2 right">
                <Card.Header style={{textAlign: "center", color: "orange"}}>
                    My chats
                </Card.Header>
                <Card
                    bg="dark"
                    key={"dark"}
                    style={{marginTop: 10, height: "100%"}}>
                    <Card.Body style={{display: "flex", justifyContent: "space-between"}} id="friends-list">
                        <div>
                            {penFriends.map((friend, index) => {
                                return <h5>
                                    <Button style={{width: 200}} variant={"warning"} key={index}>
                                        {friend.nickName}
                                    </Button>
                                </h5>
                            })}
                            <h5>
                                <Button style={{width: 200}} variant={"warning"} onClick={() => {openChat()}}>
                                    Kuba
                                </Button>
                            </h5>
                            <h5>
                                <Button style={{width: 200}} variant={"warning"}>
                                    Seba
                                </Button>
                            </h5>
                        </div>
                        <Card.Text id="chat" style={{textAlign: "center", width: "100%"}}>
                            chat with friends
                        </Card.Text>
                    </Card.Body>
                </Card>
            </Card>
        </div>
    );
};

export default MailBox;