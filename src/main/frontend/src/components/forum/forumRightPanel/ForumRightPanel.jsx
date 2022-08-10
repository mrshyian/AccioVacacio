import React, {useEffect, useState} from "react";
import "./ForumRightPanel.css"
import SinglePost from "./singlePost/SinglePost";
import {Button, Card} from "react-bootstrap";



const ForumRightPanel = (props) => {

    const [userIdInSession, setUserIdInSession] = useState(false);

    useEffect(()=>{
        IsUserInSession();
    })

    const IsUserInSession = () =>{
        sessionStorage.getItem("userId") !== null ? setUserIdInSession(true) : console.log()
    }

    return (
        <Card
            bg="dark"
            text={'white'}
            style={{width: '100%', margin: "10px"}}
            className="mb-2 right">
            <Card.Header style={{textAlign: "center", color: "orange"}}><h2>Forum</h2></Card.Header>
            <Card.Body>
                <Card.Text>
                    {props.posts.map((singlePost, index) => {
                        return (
                            <SinglePost tokenCsrf={props.tokenCsrf} post={singlePost} comments={props.comments} key={index}/>
                        )
                    })}
                </Card.Text>
            </Card.Body>
        </Card>
    );
};

export default ForumRightPanel;