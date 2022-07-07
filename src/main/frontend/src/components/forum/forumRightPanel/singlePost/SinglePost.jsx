import React, {useEffect, useState} from 'react';
import {Button, Card, FormControl, InputGroup} from "react-bootstrap";
import SingleComment from "../singleComment/SingleComment";
import "./SinglePost.css"
import AddNewComment from "../addNewComment/AddNewComment";
import {FaHeart, FaTrash} from "react-icons/fa";
import axios from "axios";
import {RiFileEditFill} from "react-icons/ri";

const SinglePost = (props) => {

    let text = props.post.postText;
    const [postText, setPostText] = useState(text)
    const [editable, setEditable] = useState(false)
    const url = "http://localhost:8080/post_edit"
    let like = 0;
    const AddLike = () => {

        like = like + 1
        like <= 1 ? sendLike() : console.log("już dodałeś like")
    }

    const sendLike = () => {
        axios.post(
            "http://localhost:8080/add_like_to_post", {
                postId: props.post.id
            })
            .then((() => reload()
            ));
    }

    const DeletePost = () => {
        axios.put(
            "http://localhost:8080/delete_post", {
                postId: props.post.id
            })
            .then((() => reload()
            ));
    }

    function submit(e) {
        e.preventDefault();
        axios.put(url, {
            postText: postText,
            postId: props.post.id
        }).then(() => reload())

    }

    function reload() {
        window.location.reload()
    }

    function editText(e) {
        e.preventDefault();
        setEditable(true);
    }


    return (
        <div>
            {editable ?
                <Card
                    bg="dark"
                    key={"dark"}
                    text={'white'}
                    style={{width: '80%', margin: "10px", marginLeft: "auto", marginRight: "auto"}}
                    className="mb-2">
                    <Card.Header style={{justifyContent: "space-between", color: "orange", display: "flex"}}>
                        <p style={{marginBottom: -20}}>
                            <img className="imgForForum"
                                 src="https://media-exp1.licdn.com/dms/image/C4D03AQGdyWRtTOqpUg/profile-displayphoto-shrink_200_200/0/1616239437610?e=1659571200&v=beta&t=pTuXFgcCY0aLZhgx3Q6zpsLhfS9fo69n__YaWFKOIEE"
                                 alt="user photo"/>
                            <p> {props.post.userName} </p></p>
                        <h2 style={{marginTop: "auto", marginBottom: "auto"}}>{props.post.topic}</h2>
                        <p>{props.post.postDateTime}</p>
                    </Card.Header>
                    <Card.Body>
                        <Card.Text>

                            <InputGroup style={{marginLeft: "-12.5%", width: "125%"}}>
                                <FormControl
                                    id="note-input-id"
                                    className="note-input"
                                    as="textarea"
                                    aria-label="With textarea"
                                    value={postText}
                                    onChange={(e) => setPostText(e.target.value)}/>
                            </InputGroup>
                            <Button variant="warning" className="save-note-button"
                                    onClick={(e) => submit(e)}>Save</Button>

                        </Card.Text>

                    </Card.Body>
                    <Card.Footer><Button onClick={DeletePost} style={{marginLeft: "93%"}} variant="outline-warning">{
                        <FaTrash/>}</Button><p></p>
                        <Button onClick={AddLike} style={{marginLeft: "93%"}} variant="outline-warning">{
                            <FaHeart/>}</Button>
                        <p>Comments:</p>
                        {props.comments.map((comment, post, index) => {
                            if(comment.post.id === props.post.id){
                                return (
                                    <SingleComment key={index} comments={comment}/>
                                )
                            }
                        })}
                        <AddNewComment postId={props.post.id}/>
                    </Card.Footer>
                </Card>
                :
                <Card
                    bg="dark"
                    key={"dark"}
                    text={'white'}
                    style={{width: '80%', margin: "10px", marginLeft: "auto", marginRight: "auto"}}
                    className="mb-2">
                    <Card.Header style={{justifyContent: "space-between", color: "orange", display: "flex"}}>
                        <p style={{marginBottom: -20}}>
                            <img className="imgForForum"
                                 src="https://media-exp1.licdn.com/dms/image/C4D03AQGdyWRtTOqpUg/profile-displayphoto-shrink_200_200/0/1616239437610?e=1659571200&v=beta&t=pTuXFgcCY0aLZhgx3Q6zpsLhfS9fo69n__YaWFKOIEE"
                                 alt="user photo"/>
                            <p> {props.post.userName} </p></p>
                        <h2 style={{marginTop: "auto", marginBottom: "auto"}}>{props.post.topic}</h2>
                        <p>{props.post.postDateTime}</p>
                    </Card.Header>
                    <Card.Body>
                        <Card.Text>
                            <h4>{props.post.postText}</h4>
                        </Card.Text>

                    </Card.Body>
                    <Card.Footer><Button onClick={DeletePost} style={{marginLeft: "93%"}} variant="outline-warning">{
                        <FaTrash/>}</Button><p></p>
                        <Button onClick={AddLike} style={{marginLeft: "93%"}} variant="outline-warning">{
                            <FaHeart/>}</Button>
                        <Button style={{marginLeft: "93%"}} variant="outline-warning" className="save-note-button"
                                onClick={(e) => editText(e)}>{<RiFileEditFill/>}</Button>
                        <p>Comments:</p>
                        {props.comments.map((comment, post, index) => {
                            if(comment.post.id === props.post.id){
                                return (
                                    <SingleComment key={index} comments={comment}/>
                                )
                            }
                        })}
                        <AddNewComment postId={props.post.id}/>
                    </Card.Footer>
                </Card>
            }


        </div>
    );
};

export default SinglePost;