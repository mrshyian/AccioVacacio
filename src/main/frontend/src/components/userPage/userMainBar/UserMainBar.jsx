import React from 'react';
import InformationAboutUser from "./userMainBarComponents/informationAboutUser/InformationAboutUser";
import ProfileImage from "./userMainBarComponents/profileImage/ProfileImage";
import SocialMedia from "./userMainBarComponents/socialMedia/SocialMedia";
import CountryCounter from "./userMainBarComponents/countryCounter/CountryCounter";
import {Card} from "react-bootstrap";
import "./UserMainBar.css"

const UserMainBar = () => {
    return (
        <Card
            bg={"dark"}
            key={"dark"}
            text={'white'}
            className="mb-2 bg-opacity"
        >
            <Card.Header style={{textAlign: "center", color: "orange"}}><h2>User Page</h2></Card.Header>
            <Card.Body>
                <Card.Text>
                    <div style={{justifyContent: "space-between", display: "flex"}}>
                        <div style={{display: "block"}}>
                            <InformationAboutUser/>
                            <CountryCounter/>
                        </div>
                        <ProfileImage/>
                    </div>
                    <SocialMedia/>
                </Card.Text>
            </Card.Body>
        </Card>
    );
};

export default UserMainBar;