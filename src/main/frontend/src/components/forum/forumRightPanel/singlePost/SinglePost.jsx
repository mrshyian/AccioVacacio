import React from 'react';
import {Button, Card} from "react-bootstrap";
import SingleComment from "../singleComment/SingleComment";
import "./SinglePost.css"
import AddNewComment from "../addNewPost/AddNewComment";
import {FaHeart} from "react-icons/fa";
import axios from "axios";
import {logDOM} from "@testing-library/react";

const SinglePost = (props) => {
    // console.log(props.user)
    // console.log(props.comments)
    let like = 0;

    const AddLike = () =>{
        like = like + 1
        // console.log(like)
        like <= 1 ? sendLike() : console.log("już dodałeś like")
    }

    const sendLike = () =>{

        axios.post(
            "http://localhost:8080/add_like_to_post",{
                postId: props.post.id
            })
            .then((r => console.log(r.data)
            ));
    }

    return (
        <Card
            bg="dark"
            key={"dark"}
            text={'white'}

            style={{width: '80%', margin: "10px", marginLeft: "auto", marginRight: "auto"}}
            className="mb-2"
        >
            <Card.Header style={{justifyContent: "space-between", color: "orange", display: "flex"}}>

                        <p style={{ marginBottom: -20 }}>
                            <img className="imgForForum" src="https://media-exp1.licdn.com/dms/image/C4D03AQGdyWRtTOqpUg/profile-displayphoto-shrink_200_200/0/1616239437610?e=1659571200&v=beta&t=pTuXFgcCY0aLZhgx3Q6zpsLhfS9fo69n__YaWFKOIEE" alt="user photo"/>
                            <p>Sebastian Ryndak  </p></p>
                        <h2 style={{marginTop: "auto", marginBottom: "auto"}}>{props.post.topic}</h2>
                        <p>{props.post.postDateTime}</p>

            </Card.Header>
            <Card.Body>
                <Card.Text>
                    <h4>{props.post.postText}</h4>

                </Card.Text>

            </Card.Body>
            <Card.Footer>
                <Button onClick={AddLike} style={{marginLeft: "93%"}} variant="outline-warning">{<FaHeart />}</Button>
                <p>Comments:</p>
                {props.post.comments.map((comment, index) => {

                    return (
                        <SingleComment key={index} comments={comment} />
                    )
                })}
                <AddNewComment postTopic={props.post.topic} postId={props.post.id}/>
            </Card.Footer>
        </Card>
    );
};

export default SinglePost;