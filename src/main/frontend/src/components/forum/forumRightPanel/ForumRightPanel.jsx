import React from "react";
import "./ForumRightPanel.css"
import SinglePost from "./singlePost/SinglePost";
import {Card} from "react-bootstrap";


const ForumRightPanel = (props) => {

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
                    {props.posts.map((singlePost, index) => {
                        return (
                            <SinglePost post={singlePost} comments={props.comments} key={index}/>
                        )
                    })}
                </Card.Text>
            </Card.Body>
        </Card>
    );
};

export default ForumRightPanel;