import React, {useEffect, useState} from 'react';
import {Button, Card} from "react-bootstrap";
import SocialMedia from "../socialMedia/SocialMedia";
import "./ProfileImage.css"
import "./InformationAboutUser.css"
import CountryCounter from "../countryCounter/CountryCounter";
import EditUserDataModal from "../../../../modals/editUserDataModal/EditUserDataModal";
import user from "../../../../../images/user.png"
import AddImage from "../addImage/AddImage";

const InformationAboutUser = (props) => {

    const [modalOpen, setModalOpen] = useState(false);

    let img = props.myUser.avatar;

    if (props.myUser.avatar === "") {
        img = user;
    }


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
                    <img className="profile-image" src={`http://localhost:8080/image/download/user/${props.myUser.id}`} alt="some image" />
                </div>
                <div className="user-info-second-div">
                    <h3>{props.myUser.fullName}</h3>
                    <div className="user-info-second-div-nickname">
                        <img className="user-info-nickname-img" src={user} />
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
                    <Button onClick={() => setModalOpen(true)} className="user-info-edit-btn" variant={"outline-warning"}>Edit my details</Button>
                    <SocialMedia myUser={props.myUser}/>
                </div>
            </Card.Body>
            <CountryCounter/>
            {modalOpen && <EditUserDataModal visible={modalOpen} close={setModalOpen} myUser={props.myUser}/>}
        </Card>
    );
};

export default InformationAboutUser;