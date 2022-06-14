import React from 'react';
import {Card} from "react-bootstrap";
import "./ForumRightPanel.css"
import SingleComment from "./singleComment/SingleComment";
import SinglePost from "./singlePost/SinglePost";

const Right = (props) => {
    return (
        <Card
            bg="dark"
            key={"dark"}
            text={'white'}

            style={{width: '100%', margin: "10px"}}
            className="mb-2 right">
            <Card.Header style={{textAlign: "center", color: "orange"}}><h2>Forum</h2></Card.Header>
            <Card.Body>
                <Card.Text>
                    <SinglePost/>
                </Card.Text>
            </Card.Body>
        </Card>
    );
};

export default Right;