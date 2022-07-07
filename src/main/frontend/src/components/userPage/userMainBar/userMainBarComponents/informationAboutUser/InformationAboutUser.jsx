import React from 'react';
import {Button, Card} from "react-bootstrap";
import SocialMedia from "../socialMedia/SocialMedia";
import "./ProfileImage.css"
import "./InformationAboutUser.css"
import nickname from "../../../../../images/name_user_3716.png"


const InformationAboutUser = (props) => {
    return (
        <Card
            bg={"dark"}
            key={"dark"}
            text={'white'}
            className="mb-2"
            style={{width: "100%"}}
        >
            <Card.Body className="user-info-body">
                <div>
                    <img className="profile-image" src="https://upload.wikimedia.org/wikipedia/commons/thumb/7/79/TwinkleLab_First_Fiddle.jpg/1200px-TwinkleLab_First_Fiddle.jpg" alt="some image" />
                </div>
                <div className="user-info-second-div">
                    <h3>{props.myUser.fullName}</h3>
                    <div className="user-info-second-div-nickname">
                        <img className="user-info-nickname-img" src={nickname} />
                        <h4 className="user-info-nickname">{props.myUser.nickName}</h4>
                    </div>
                    <Card.Text className="user-info-aboutme-text">
                        <h5 style={{color: "orange"}}>About me:</h5>
                        <div className="user-info-aboutme-text-div">
                            {props.myUser.aboutMe}
                        </div>
                    </Card.Text>
                </div>
                <div>
                    <Button className="user-info-edit-btn" variant={"outline-warning"}>Edit my details</Button>
                    <SocialMedia myUser={props.myUser}/>
                </div>

            </Card.Body>
        </Card>
    );
};

export default InformationAboutUser;