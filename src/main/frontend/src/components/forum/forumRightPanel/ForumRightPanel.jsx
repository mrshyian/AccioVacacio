import React from 'react';
import {Card} from "react-bootstrap";
import "./ForumRightPanel.css"
import SinglePost from "./singlePost/SinglePost";


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

                    {/*<SinglePost comments={props.comments}/>*/}
                    {/*<Button variant="outline-warning" onClick={() => openModal()} icon={<FaCommentDots />}>Add Post</Button>*/}
                    {/*{NewModalOpen && <NewPostModal open={NewModalOpen}/>}*/}
                    {/*{props.comments.city}*/}
                </Card.Text>
            </Card.Body>
        </Card>
    );
};

export default ForumRightPanel;