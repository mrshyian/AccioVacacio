import React, {useState} from 'react';
import {Card} from "react-bootstrap";
import InformationAboutUser from "../../../userMainBar/userMainBarComponents/informationAboutUser/InformationAboutUser";
import UserLeftBar from "../../UserLeftBar";
import {getResponseFromAxiosGet} from "../../../../../axios";

const FriendPage = () => {
    const [myFriend, setMyFriend] = useState({})

    const userNickName = (window.location.href.toString().split("/")[window.location.href.toString().split("/").length-1]);
    const friendByNickUrl = `http://localhost:8080/get_friend_by_nick/${userNickName}`;
    getResponseFromAxiosGet(friendByNickUrl,2)
        .then(res => {
            sessionStorage.setItem("chosenFriendId", res.data.id);
            setMyFriend(res.data)
        });


    return (
        <div>
            <UserLeftBar/>
            <Card
                bg={"dark"}
                key={"friend-page-dark"}
                text={'white'}
                className="mb-2 bg-opacity"
            >
                <Card.Body>
                    <Card.Text style={{paddingLeft: "5%", paddingRight: "5%"}}>
                        <InformationAboutUser />
                    </Card.Text>
                </Card.Body>
            </Card>
        </div>
    );
};

export default FriendPage;