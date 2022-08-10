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
import {postDataToServerByAxiosPost, putDataToServerByAxiosPut} from "../../../../axios";

const SinglePost = (props) => {
    const userId = props.post.myUserTable.id
    let text = props.post.postText;
    const [postText, setPostText] = useState(text)
    const [editable, setEditable] = useState(false)
    const url = "http://localhost:8080/post_edit"
    let like = 0;
    const AddLike = () => {
        like = like + 1
        like <= 1 ? sendLike() : console.log()
    }

    const sendLike = () => {
        const data={
            postId: props.post.id
        }
        postDataToServerByAxiosPost("http://localhost:8080/add_like_to_post", data, 0).then();
    }

    const DeletePost = () => {
        const data={
                postId: props.post.id
            }
        putDataToServerByAxiosPut("http://localhost:8080/delete_post", data, 2).then(() => reload())
    }

    function submit(e) {
        e.preventDefault();
        const data={
            postText: postText,
            postId: props.post.id
        }
        putDataToServerByAxiosPut(url, data, 0).then(() => reload())
    }

    useEffect(()=>{
        postOwner()
    }, [])

    const [isOwner, setIsOwner] = useState(false)

    const postOwner = () => {
        if(sessionStorage.getItem("userId") == userId ) {
            setIsOwner(true)
        }else{
            setIsOwner(false)
        }

    }

    function reload() {
        window.location.reload()
    }

    function editText(e) {
        e.preventDefault();
        setEditable(true);
        reload();
    }

    return (
        <div id={props.post.id} style={{marginBottom: "100px"}}>
            {editable ?
                <Card
                    bg="dark"
                    text={'white'}
                    style={{width: '80%', margin: "10px", marginLeft: "auto", marginRight: "auto"}}
                    className="mb-2">
                    <Card.Header style={{justifyContent: "space-between", color: "orange", display: "flex"}}>
                        <div>
                            <img
                                className="imgForForum"
                                src={`http://localhost:8080/image/download/post/profile/${props.post.id}`}
                                onError={({ currentTarget }) => {
                                    currentTarget.onerror = null; // prevents looping
                                    currentTarget.src=userImage;
                                }}
                            />
                            <div> {props.post.userName} </div></div>
                        <h2 style={{marginTop: "auto", marginBottom: "auto"}}>{props.post.topic}</h2>
                        <div>{props.post.postDateTime}</div>
                    </Card.Header>
                    <Card.Body>
                        <Card.Text>

                            <InputGroup style={{marginLeft: "-12.5%", width: "125%"}}>
                                <FormControl
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
                        <FaTrash/>}</Button>
                        <Button onClick={AddLike} style={{marginLeft: "93%"}} variant="outline-warning">{
                            <FaHeart/>}</Button>
                        <Button href="#top" style={{marginLeft: "5px"}} variant="outline-warning" className="save-note-button"
                        >{<BsFillArrowUpSquareFill/>}</Button>
                            <div>Comments:</div>
                        {props.comments.map((comment, index) => {
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
                    text={'white'}
                    style={{width: '80%', margin: "10px", marginLeft: "auto", marginRight: "auto"}}
                    className="mb-2">
                    <Card.Header style={{justifyContent: "space-between", color: "orange", display: "flex"}}>
                        <div>
                            <img
                                className="imgForForum"
                                src={`http://localhost:8080/image/download/post/profile/${props.post.id}`}
                                onError={({ currentTarget }) => {
                                    currentTarget.onerror = null; // prevents looping
                                    currentTarget.src=userImage;
                                }}
                            />
                            <div> {props.post.userName} </div></div>
                        <h2 style={{marginTop: "auto", marginBottom: "auto"}}>{props.post.topic}</h2>
                        <div>{props.post.postDateTime}</div>
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
                        {sessionStorage.getItem("userId")!== null ?
                        <div><div style={{display: "flex", justifyContent:"right"}} >
                            {isOwner?
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
                        <AddNewComment postId={props.post.id}/></div>
                            :
                            <div style={{display: "flex", justifyContent:"right"}} >
                            <Button href="#top" style={{marginLeft: "5px"}} variant="outline-warning" className="save-note-button"
                            >{<BsFillArrowUpSquareFill/>}</Button>
                            </div>
                        }
                        <div>Comments:</div>
                        {props.comments.map((comment, index) => {
                            if(comment.post.id === props.post.id){
                                return (
                                    <SingleComment tokenCsrf={props.tokenCsrf} key={index} comments={comment}/>
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