import React, {useEffect, useState} from 'react';
import {Button, Card, FormControl, InputGroup} from "react-bootstrap";
import SingleComment from "../singleComment/SingleComment";
import "./SinglePost.css"
import AddNewComment from "../addNewComment/AddNewComment";
import {FaHeart, FaTrash} from "react-icons/fa";
import axios from "axios";
import {RiFileEditFill} from "react-icons/ri";
import { BsFillArrowUpSquareFill} from "react-icons/bs";
import userImage from "../../../../images/user.png";

const SinglePost = (props) => {
    const userId = props.post.myUserTable.id
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

    useEffect(()=>{
        isSession()
    }, [])

    const [session, setSession] = useState(false)
    const isSession = () => {
        if(sessionStorage.getItem("userId") == userId ) {
            setSession(true)
        }else{
            setSession(false)
        }
    }

    function reload() {
        window.location.reload()
    }

    function editText(e) {
        e.preventDefault();
        setEditable(true);
    }


    return (
        <div id={props.post.id} style={{marginBottom: "100px"}}>
            {editable ?
                <Card
                    bg="dark"
                    key={"dark"}
                    text={'white'}
                    style={{width: '80%', margin: "10px", marginLeft: "auto", marginRight: "auto"}}
                    className="mb-2">
                    <Card.Header style={{justifyContent: "space-between", color: "orange", display: "flex"}}>
                        <p style={{marginBottom: -20}}>
                            <img
                                className="imgForForum"
                                src={`http://localhost:8080/image/download/post/profile/${props.post.id}`}
                                onError={({ currentTarget }) => {
                                    currentTarget.onerror = null; // prevents looping
                                    currentTarget.src=userImage;
                                }}
                            />
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
                        <Button href="#top" style={{marginLeft: "5px"}} variant="outline-warning" className="save-note-button"
                        >{<BsFillArrowUpSquareFill/>}</Button>

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
                            <img
                                className="imgForForum"
                                src={`http://localhost:8080/image/download/post/profile/${props.post.id}`}
                                onError={({ currentTarget }) => {
                                    currentTarget.onerror = null; // prevents looping
                                    currentTarget.src=userImage;
                                }}
                            />
                            <p> {props.post.userName} </p></p>
                        <h2 style={{marginTop: "auto", marginBottom: "auto"}}>{props.post.topic}</h2>
                        <p>{props.post.postDateTime}</p>
                    </Card.Header>
                    <Card.Body>

                        <Card.Text>

                            <h4>{props.post.postText}</h4>
                            <img className="post-image"
                                 src={`http://localhost:8080/image/download/post/${props.post.id}`}
                                 alt=""/>
                        </Card.Text>

                    </Card.Body>
                    <Card.Footer>
                        <div style={{display: "flex", justifyContent:"right"}} >
                            {session?
                                <div>
                                    <Button onClick={DeletePost} style={{marginLeft: "5px"}} variant="outline-warning">{<FaTrash/>}</Button>


                                    <Button onClick={AddLike} style={{marginLeft: "5px"}} variant="outline-warning">{<FaHeart/>}</Button>


                                    <Button style={{marginLeft: "5px"}} variant="outline-warning" className="save-note-button"
                                            onClick={(e) => editText(e)}>{<RiFileEditFill/>}</Button>


                                    <Button href="#top" style={{marginLeft: "5px"}} variant="outline-warning" className="save-note-button"
                                    >{<BsFillArrowUpSquareFill/>}</Button>
                                </div>
                                :
                                <div>
                                    <Button onClick={AddLike} style={{marginLeft: "5px"}} variant="outline-warning">{<FaHeart/>}</Button>
                                    <Button href="#top" style={{marginLeft: "5px"}} variant="outline-warning" className="save-note-button"
                                    >{<BsFillArrowUpSquareFill/>}</Button>
                                </div>
                            }

                        </div>
                        <AddNewComment postId={props.post.id}/>
                        <h5>Comments:</h5>
                        {props.comments.map((comment, post, index) => {
                            if(comment.post.id === props.post.id){
                                return (
                                    <SingleComment key={index} comments={comment}/>
                                )
                            }
                        })}
                    </Card.Footer>
                </Card>
            }


        </div>
    );
};

export default SinglePost;