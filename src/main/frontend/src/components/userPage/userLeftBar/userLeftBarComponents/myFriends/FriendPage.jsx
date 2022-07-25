import React from 'react';
import {Card} from "react-bootstrap";
import InformationAboutUser from "../../../userMainBar/userMainBarComponents/informationAboutUser/InformationAboutUser";
import {useLocation} from "react-router-dom";
import UserLeftBar from "../../UserLeftBar";

const FriendPage = () => {

    const location = useLocation()
    const friend = location.state.friend;
    window.history.pushState(null, '', `/userpage/friend/${friend.nickName}`);
    return (
        <div>
            <UserLeftBar/>
            <Card
                bg={"dark"}
                key={"dark"}
                text={'white'}
                className="mb-2 bg-opacity"
            >
                <Card.Body>
                    <Card.Text style={{paddingLeft: "5%", paddingRight: "5%"}}>
                        <InformationAboutUser myUser={friend}/>
                    </Card.Text>
                </Card.Body>
            </Card>
        </div>
    );
};

export default FriendPage;