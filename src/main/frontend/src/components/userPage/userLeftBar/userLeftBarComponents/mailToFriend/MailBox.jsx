import React, {useEffect, useState} from 'react';
import UserLeftBar from "../../UserLeftBar";
import {Button, Card} from "react-bootstrap";
import axios from "axios";
import Chat from "./Chat";
import {useLocation} from "react-router-dom";

const MailBox = () => {

    const changeCSSButton = (index) => {
        penFriends.forEach((fr, i) => {
            if (fr.id.toString() !== sessionStorage.getItem("userId")){
                if (i.toString()+"friend"===index){
                    document.getElementById(index).style.backgroundColor = "rgb(122,0,225)"
                } else {
                    document.getElementById(i+"friend").style.backgroundColor = "rgb(255, 195, 0)"
                }
            }
        })
    }

    let friend={};

    try{
        const location = useLocation()
        friend = location.state.friend;
    } catch(e) { console.error(e); }


    const [penFriends, setPenFriends] = useState([]);

    const [showChat, setShowChat] = useState({});

    const getAllPenFriends =  () => {
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
        (async () => {
            getAllPenFriends();
            setShowChat(friend);
        })()
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
                                        <Button
                                            style={{width: 200}}
                                            id={index.toString() + "friend"}
                                            variant={"warning"}
                                            onClick={() => {
                                                setShowChat(friend);
                                                changeCSSButton(index.toString() + "friend");
                                            }}
                                        >
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