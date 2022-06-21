import React from 'react';
import InformationAboutUser from "./userMainBarComponents/informationAboutUser/InformationAboutUser";
import ProfileImage from "./userMainBarComponents/profileImage/ProfileImage";
import SocialMedia from "./userMainBarComponents/socialMedia/SocialMedia";
import {Card} from "react-bootstrap";

const UserMainBar = () => {
    return (
        <div>
            <Card
                bg={"dark"}
                key={"dark"}
                text={'white'}
                className="mb-2"
            >
                <Card.Header style={{textAlign: "center", color: "orange"}}><h2>User Page</h2></Card.Header>
                <Card.Body>
                    <Card.Text>
                        <div style={{textAlign: "center"}}>
                            <img className="img-for-crime-rating"
                                 src={""}
                                 alt="random image"/>
                        </div>
                    </Card.Text>
                </Card.Body>
            </Card>
            <InformationAboutUser/>
            <ProfileImage/>
            <SocialMedia/>
        </div>
    );
};

export default UserMainBar;