import React, {useEffect, useState} from 'react';
import InformationAboutUser from "./userMainBarComponents/informationAboutUser/InformationAboutUser";
import {Card} from "react-bootstrap";
import "./UserMainBar.css"
import {getResponseFromAxiosGet} from "../../../axios";

const UserMainBar = () => {
    const userMainBarUrl = `http://localhost:8080/usermainbar/${sessionStorage.getItem('userId')}`;
    const [myUser, setMyUser] = useState({});

    const getUserFromDB = () => {
        getResponseFromAxiosGet(userMainBarUrl, 2).then(resp => setMyUser(resp.data));
    };

    useEffect(() => {
        getUserFromDB();
    }, []);


    return (
        <Card
            bg={"dark"}
            key={"user-main-bar-dark"}
            text={'white'}
            className="mb-2 bg-opacity"
        >
            <Card.Body>
                <Card.Text style={{paddingLeft: "5%", paddingRight: "5%"}}>
                    <InformationAboutUser myUser={myUser}/>
                </Card.Text>
            </Card.Body>
        </Card>
    );
};



export default UserMainBar;