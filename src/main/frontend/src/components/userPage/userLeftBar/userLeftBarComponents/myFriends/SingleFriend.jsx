import React from 'react';
import {Button, Card} from "react-bootstrap";
import {FaComments, FaTrashAlt} from "react-icons/fa";
import {useNavigate} from "react-router-dom";
import "./Friends.css"
import userImage from "../../../../../images/user.png";
import {getResponseFromAxiosGet} from "../../../../../axios";

const SingleFriend = (props) => {
    const navigate = useNavigate();
    const removeFriendUrl = `http://localhost:8080/remove_friend/${props.myUser.id}`;
    const messageToFriend = `http://localhost:8080/mail_to_friend/${props.myUser.id}`;



    const removeFriend = () => {
        getResponseFromAxiosGet(removeFriendUrl,2).then(() => window.location.reload());
    };

    const sayHello = () => {
        getResponseFromAxiosGet(messageToFriend,2)
            .then(() => {
                navigate("/userpage/friends/mail_box", {
                    state: {
                        friend: props.myUser
                    }})});
    }


    return (
        <Card
            bg="dark"
            style={{border: "1px solid orange", float: "left", width: "46%", margin: 10}}>
            <Card.Body>
                <img
                    style={{width: 100, height: 100, float: "left"}}
                    src={`http://localhost:8080/image/download/user/${props.myUser.id}`}
                    onError={({ currentTarget }) => {
                        currentTarget.onerror = null; // prevents looping
                        currentTarget.src=userImage;
                    }}
                />
                <h4 className="link-to-friend-page" onClick={() => {
                    navigate(`/userpage/friend/${props.myUser.nickName}`)
                }}>{props.myUser.fullName}</h4>
                <Button style={{float: "right", margin: "3px"}} variant="outline-warning" onClick={() => removeFriend()}>{<FaTrashAlt/>}</Button>
                <Button style={{float: "right", margin: "3px"}} variant="outline-warning"
                        onClick={() => sayHello()}>{<FaComments/>}</Button>
            </Card.Body>
        </Card>
    );
};

export default SingleFriend;
