import React from 'react';
import {Button, Card} from "react-bootstrap";
import avatar from "../../../../../images/user.png"
import {FaComments, FaTrashAlt} from "react-icons/fa";

const SingleFriend = (props) => {
    return (
        <Card
            bg="dark"
            key={"dark"}
            style={{border: "1px solid orange", float: "left", width: "46%", margin: 10}}>
            <Card.Body>
                <img style={{width: 70, height: 70, float: "left"}} src={avatar} alt="some image" />
                {/*<img style={{width: 100, height: 100, float: "left"}} src={`http://localhost:8080/image/download/user/${props.myUser.id}`} alt="some image" />*/}
                <h4 style={{marginTop: "5%"}}>Friend full name</h4>
                {/*<h4 style={{marginTop: "5%"}}>{props.myUser.fullName}</h4>*/}
                <Button style={{float: "right", margin: "3px"}} variant="outline-warning">{<FaTrashAlt/>}</Button>
                <Button style={{float: "right", margin: "3px"}} variant="outline-warning">{<FaComments/>}</Button>
            </Card.Body>
        </Card>
    );
};

export default SingleFriend;
