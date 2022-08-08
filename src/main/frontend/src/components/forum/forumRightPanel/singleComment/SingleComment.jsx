import React, {useEffect, useState} from "react";
import {Button, Card, FormControl, Image, InputGroup} from "react-bootstrap";
import "./SingleComment.css"
import {FaHeart, FaTrash} from "react-icons/fa";

import {RiFileEditFill} from "react-icons/ri";
import userImage from "../../../../images/user.png";
import {getResponseFromAxiosGet, postDataToServerByAxiosPost, putDataToServerByAxiosPut} from "../../../../axios";


const SingleComment = (props) => {
    const editCommentUrl = "http://localhost:8080/comment_edit";
    // const csrfTokenUrl = "http://localhost:8080/token";
    const deleteCommentUrl = "http://localhost:8080/delete_comment";
    const addLikeToCommentUrl = "http://localhost:8080/add_like_to_comment";

    let text = props.comments.commentText;
    const userId = props.comments.myUserTable.id;
    const userInSessionId = sessionStorage.getItem("userId");

    const [commentText, setCommentText] = useState(text);
    const [token, setToken] = useState("");
    const [editable, setEditable] = useState(false);
    const [isOwner, setIsOwner] = useState(false);

    let like = 0;

    const commentOwner = () => {
        if (userInSessionId == userId ){
            setIsOwner(true);
        } else {
            setIsOwner(false);
        }

    }

    useEffect(() => {
        commentOwner()
    }, [])

    function editText(e) {
        e.preventDefault();
        setEditable(true);
    }

    const AddLike = () => {
        like++;
        like <= 1 ? sendLikeData() : console.log("już dodałeś like");
        reload();
    }

    const sendLikeData = async () => {
        const data = {
            commentId: props.comments.id
        };
        alert(props.comments.id)

        await postDataToServerByAxiosPost(addLikeToCommentUrl, data, 2);
        reload();
    }

    const DeleteComment = async () => {
        const data = {
            commentId: props.comments.id
        };

        await putDataToServerByAxiosPut(deleteCommentUrl, data, 2);
        reload();
    }

    async function submit(e) {
        e.preventDefault();

        const editCommentData = {
            commentText: commentText,
            commentId: props.comments.id
        };

        // const resp = getResponseFromAxiosGet(csrfTokenUrl, 2);
        // setToken(resp.data);

        await putDataToServerByAxiosPut(editCommentUrl, editCommentData, 2);
        reload();
    }

    function reload() {
        window.location.reload()
    }

    return (
        <div>
            {editable ?
                <Card
                    text={'white'}
                    style={{maxWidth: '90%', margin: "10px", marginLeft: "2.5%"}}
                    className="mb-2 ">
                    <Card.Header style={{
                        justifyContent: "space-between",
                        color: "orange",
                        display: "flex",
                        backgroundColor: "rgb(35, 35, 35)"
                    }}>
                        <div>
                            <Image
                                fluid="true"
                                className="imgForForum"
                                src={`http://localhost:8080/image/download/comment/profile/${props.comments.id}`}
                                onError={({currentTarget}) => {
                                    currentTarget.onerror = null; // prevents looping
                                    currentTarget.src = userImage;
                                }}
                            />
                            <div>{props.comments.userName}</div>
                        </div>
                        <div>{props.comments.commentDateTime}</div>

                    </Card.Header>
                    <Card.Body style={{backgroundColor: "rgb(55, 55, 55)"}}>
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

                            <div><Image rounded="true" fluid="true" className="addImage"
                                      src={`http://localhost:8080/image/download/comment/${props.comments.id}`} alt=""/>
                            </div>
                        </Card.Text>
                    </Card.Body>
                    <Card.Footer style={{backgroundColor: "rgb(45, 45, 45)"}}>
                    </Card.Footer>
                </Card>

                :

                <Card
                    text={'white'}
                    style={{maxWidth: '90%', margin: "10px", marginLeft: "2.5%"}}
                    className="mb-2 ">
                    <Card.Header style={{
                        justifyContent: "space-between",
                        color: "orange",
                        display: "flex",
                        backgroundColor: "rgb(35, 35, 35)"
                    }}>

                        <div>
                            <Image className="imgForForum"
                                   src={`http://localhost:8080/image/download/comment/profile/${props.comments.id}`}
                                   alt=""/>
                            <div>{props.comments.userName}</div></div>
                        <div>{props.comments.commentDateTime}</div>

                    </Card.Header>
                    <Card.Body style={{backgroundColor: "rgb(55, 55, 55)"}}>
                        <Card.Text style={{color: "white"}}>
                            <h4>{props.comments.commentText}</h4>

                            <br/><div><Image rounded="true" fluid="true" className="addImage"
                                      src={`http://localhost:8080/image/download/comment/${props.comments.id}`} alt=""/>
                            </div>
                        </Card.Text>
                    </Card.Body>
                    <Card.Footer style={{backgroundColor: "rgb(45, 45, 45)"}}>
                        {sessionStorage.getItem("userId") !== null ?
                            <div style={{display: "flex", justifyContent: "right"}}>
                                {isOwner ?
                                    <div>
                                        <Button onClick={DeleteComment} style={{marginLeft: "5px"}}
                                                variant="outline-warning">{< FaTrash/>}</Button>
                                        <Button onClick={AddLike} style={{marginLeft: "5px"}}
                                                variant="outline-warning">{
                                            <FaHeart/>}</Button>
                                        <Button style={{marginLeft: "5px"}} variant="outline-warning"
                                                className="save-note-button"
                                                onClick={(e) => editText(e)}>{<RiFileEditFill/>}</Button>
                                    </div>
                                    :
                                    <div>
                                        <Button onClick={AddLike} style={{marginLeft: "5px"}}
                                                variant="outline-warning">{
                                            <FaHeart/>}</Button>
                                    </div>
                                }
                            </div>
                            :
                            <div/>
                        }
                    </Card.Footer>
                </Card>}
        </div>
    );
};

export default SingleComment;