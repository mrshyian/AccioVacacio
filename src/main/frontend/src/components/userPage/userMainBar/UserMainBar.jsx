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
            <Card.Body>
                <Card.Text>
                    <InformationAboutUser/>
                    <CountryCounter/>
                </Card.Text>
            </Card.Body>
        </Card>
    );
};

export default UserMainBar;