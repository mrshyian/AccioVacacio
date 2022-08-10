import React from 'react';
import {Button, Card} from "react-bootstrap";
import {FaUserPlus} from "react-icons/fa";
import userImage from "../../../../../images/user.png";
import {getResponseFromAxiosGet} from "../../../../../axios";

const SingleSearchedFriend = (props) => {
    const addFriendUrl = `http://localhost:8080/add_friend/${props.myUser.id}`;

    const addFriend = () => {
        getResponseFromAxiosGet(addFriendUrl,2).then(() => window.location.reload());
    };

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
                <h4 style={{marginTop: "5%"}}>{props.myUser.fullName}</h4>
                <Button style={{float: "right", margin: "3px"}} variant="outline-warning" onClick={() => addFriend()}>{<FaUserPlus/>}</Button>
            </Card.Body>
        </Card>
    );
};

export default SingleSearchedFriend;