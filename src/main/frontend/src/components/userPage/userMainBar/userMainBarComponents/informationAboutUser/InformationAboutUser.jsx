import React, {useState} from 'react';
import {Button, Card} from "react-bootstrap";
import SocialMedia from "../socialMedia/SocialMedia";
import "./ProfileImage.css"
import "./InformationAboutUser.css"
import nickname from "../../../../../images/name_user_3716.png"
import CountryCounter from "../countryCounter/CountryCounter";
import EditUserDataModal from "../../../../modals/editUserDataModal/EditUserDataModal";
import user from "../../../../../images/user.png"

const InformationAboutUser = (props) => {

    const [modalOpen, setModalOpen] = useState(false);

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
                    <img className="profile-image" src={user} alt="some image" />
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