import React, {useEffect, useState} from "react";
import {Button, Card, FormControl, Image, InputGroup} from "react-bootstrap";
import "./SingleComment.css"
import {FaHeart, FaTrash} from "react-icons/fa";

import axios from "axios";
import {RiFileEditFill} from "react-icons/ri";


const SingleComment = (props) => {
    const url = "http://localhost:8080/comment_edit"
    let text = props.comments.commentText;
    const userId = props.comments.myUserTable.id
    const [commentText, setCommentText] = useState(text)


    const [editable, setEditable] = useState(false)

    let like=0;

    const AddLike = () =>{
        like = like + 1
        like <= 1 ? sendLikeData() : console.log("już dodałeś like")
        reload();
    }

    const [session, setSession] = useState(false)
    const isSession = () => {
        if(sessionStorage.getItem("userId") == userId ) {
            setSession(true)
        }else{
            setSession(false)
        }
    }

    const sendLikeData = () =>{
        axios.post(
            "http://localhost:8080/add_like_to_comment",{
                commentId: props.comments.id
            })
            .then((() => reload()

            ));
    }

    const DeleteComment = () =>{
        axios.put(
            "http://localhost:8080/delete_comment",{
                commentId: props.comments.id
            })
            .then((() => reload()

            ));
    }

    useEffect(()=>{
        isSession()
    }, [])

    function submit(e){
        e.preventDefault();
        axios.put(url, {
            commentText: commentText,
            commentId: props.comments.id
        }).then(() => reload())
    }

    function reload(){
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
                    key={"dark"}
                    text={'white'}
                    style={{maxWidth: '90%', margin: "10px", marginLeft: "2.5%"}}
                    className="mb-2 ">
                    <Card.Header style={{justifyContent: "space-between", color: "orange", display: "flex", backgroundColor: "rgb(35, 35, 35)"}}>

                        <p style={{ marginBottom: -30 }}>
                            <Image fluid="true" className="imgForPost" src={`http://localhost:8080/image/download/comment/profile/${props.comments.id}`} alt="user photo"/>
                            <p>{props.comments.userName}</p></p>
                        <p>{props.comments.commentDateTime}</p>

                    </Card.Header>
                    <Card.Body style={{ backgroundColor: "rgb(55, 55, 55)"}}>
                        <Card.Text style={{color: "white"}}>
                            <InputGroup style={{marginLeft: "-12.5%", width: "125%"}}>
                                <FormControl
                                    id="note-input-id"
                                    className="note-input"
                                    as="textarea"
                                    aria-label="With textarea"
                                    value={commentText}
                                    onChange={(e) => setCommentText(e.target.value)}/>
                            </InputGroup>
                            <Button variant="warning" className="save-note-button"
                                    onClick={(e) => submit(e)}>Save</Button>

                            <p><Image rounded="true" fluid="true" className="addImage" src={`http://localhost:8080/image/download/comment/${props.comments.id}`} alt=""/></p>
                        </Card.Text>
                    </Card.Body>
                    <Card.Footer style={{ backgroundColor: "rgb(45, 45, 45)"}}>
                    </Card.Footer>
                </Card>

                :

                <Card
                    key={"dark"}
                    text={'white'}
                    style={{maxWidth: '90%', margin: "10px", marginLeft: "2.5%"}}
                    className="mb-2 ">
                    <Card.Header style={{justifyContent: "space-between", color: "orange", display: "flex", backgroundColor: "rgb(35, 35, 35)"}}>

                        <p style={{ marginBottom: -30 }}>
                           <Image className="imgForForum" src={`http://localhost:8080/image/download/comment/profile/${props.comments.id}`} alt="user photo"/>
                            <p>{props.comments.userName}{userId}</p></p>
                        <p>{props.comments.commentDateTime}</p>

                    </Card.Header>
                    <Card.Body style={{ backgroundColor: "rgb(55, 55, 55)"}}>
                        <Card.Text style={{color: "white"}}>
                            <h4>{props.comments.commentText}</h4>

                            <p><Image rounded="true" fluid="true" className="addImage"  src={`http://localhost:8080/image/download/comment/${props.comments.id}`} alt=""/></p>
                        </Card.Text>
                    </Card.Body>
                    <Card.Footer style={{ backgroundColor: "rgb(45, 45, 45)"}}>
                        <div style={{display: "flex", justifyContent:"right"}}>
                            {session ?
                                <div>
                        <Button onClick={DeleteComment} style={{marginLeft: "5px"}} variant="outline-warning">{< FaTrash/>}</Button>
                        <Button onClick={AddLike} style={{marginLeft: "5px"}} variant="outline-warning">{<FaHeart />}</Button>
                        <Button style={{marginLeft: "5px"}} variant="outline-warning" className="save-note-button"
                                onClick={(e) => editText(e)}>{<RiFileEditFill/>}</Button>
                                </div>
                            :
                                <div>
                                    <Button onClick={AddLike} style={{marginLeft: "5px"}} variant="outline-warning">{<FaHeart />}</Button>
                                </div>
                            }
                        </div>
                    </Card.Footer>
                </Card>}
        </div>
    );
};

export default SingleComment;