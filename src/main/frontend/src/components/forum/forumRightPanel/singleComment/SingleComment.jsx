import React, {useState} from "react";
import {Button, Card, FormControl, Image, InputGroup} from "react-bootstrap";
import "./SingleComment.css"
import {FaHeart, FaTrash} from "react-icons/fa";

import axios from "axios";
import {RiFileEditFill} from "react-icons/ri";


const SingleComment = (props) => {
    const url = "http://localhost:8080/comment_edit"
    let text = props.comments.commentText;
    const [commentText, setCommentText] = useState(text)

    const [editable, setEditable] = useState(false)

    let like=0;

    const AddLike = () =>{
        like = like + 1
        like <= 1 ? sendLikeData() : console.log("już dodałeś like")
        reload();
    }

    const sendLikeData = () =>{
        axios.post(
            "http://localhost:8080/add_like_to_comment",{
                commentId: props.comments.id
            })
            .then((r => console.log(r.data)

            ));
    }

    const DeleteComment = () =>{
        axios.put(
            "http://localhost:8080/delete_comment",{
                commentId: props.comments.id
            })
            .then((r => console.log(r.data)
            ));
    }

    function submit(e){
        e.preventDefault();
        axios.put(url, {
            commentText: commentText,
            commentId: props.comments.id
        }).then(r => console.log(r.data))
        reload();
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
                            <Image fluid="true" className="imgForPost" src={props.comments.commentImage} alt="user photo"/>
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

                            <p><Image rounded="true" fluid="true" className="addImage" src="https://cdn.icon-icons.com/icons2/2184/PNG/512/healthy_strength_strong_health_heart_icon_133538.png" alt=""/></p>
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
                            <Image fluid="true" className="imgForPost" src={props.comments.commentImage} alt="user photo"/>
                            <p>{props.comments.userName}</p></p>
                        <p>{props.comments.commentDateTime}</p>

                    </Card.Header>
                    <Card.Body style={{ backgroundColor: "rgb(55, 55, 55)"}}>
                        <Card.Text style={{color: "white"}}>
                            <h4>{props.comments.commentText}</h4>

                            <p><Image rounded="true" fluid="true" className="addImage" src="https://cdn.icon-icons.com/icons2/2184/PNG/512/healthy_strength_strong_health_heart_icon_133538.png" alt=""/></p>
                        </Card.Text>
                    </Card.Body>
                    <Card.Footer style={{ backgroundColor: "rgb(45, 45, 45)"}}>
                        <Button onClick={DeleteComment} style={{marginLeft: "92%"}} variant="outline-warning">{< FaTrash/>}</Button>
                        <Button onClick={AddLike} style={{marginLeft: "92%"}} variant="outline-warning">{<FaHeart />}</Button>
                        <Button style={{marginLeft: "92%"}} variant="outline-warning" className="save-note-button"
                                onClick={(e) => editText(e)}>{<RiFileEditFill/>}</Button>
                    </Card.Footer>
                </Card>}
        </div>
    );
};

export default SingleComment;