import React from 'react';
import {Card, Image} from "react-bootstrap";
import SingleComment from "../singleComment/SingleComment";
import "./SinglePost.css"
import AddNewComment from "../addNewPost/AddNewComment";

const SinglePost = (props) => {

    return (
        <Card
            bg="dark"
            key={"dark"}
            text={'white'}
            className="mb-2 post-card"
        >
            <Card.Header className="post-card-header">
                        <div>
                            <img className="imgForForum" src="https://media-exp1.licdn.com/dms/image/C4D03AQGdyWRtTOqpUg/profile-displayphoto-shrink_200_200/0/1616239437610?e=1659571200&v=beta&t=pTuXFgcCY0aLZhgx3Q6zpsLhfS9fo69n__YaWFKOIEE" alt="user photo"/>
                            <div>Sebastian Ryndak </div>
                        </div>
                        <h2 className="post-topic">{props.post.topic}</h2>
                        <div>{props.post.postDateTime}</div>
            </Card.Header>
            <Card.Body>
                <Card.Text>
                    <div className="post-content-div">
                        <Image rounded="true" fluid="true" className="post-image"
                               src="https://c.tenor.com/DthVL3IQLREAAAAC/cat-funny-cat.gif"
                               alt=""/>
                        <h4 className="post-text">{props.post.postText}</h4>
                    </div>
                    {sessionStorage.getItem("userId") === null ? (
                        <div/>
                    ) : (
                        <AddNewComment postTopic={props.post.topic} postId={props.post.id}/>
                    )}
                </Card.Text>
            </Card.Body>
            <Card.Footer>
                <h3>Comments:</h3>
                {props.post.comments.map((comment, index) => {

                    return (
                        <SingleComment key={index} comments={comment}/>
                    )
                })}
            </Card.Footer>
        </Card>
    );
};

export default SinglePost;