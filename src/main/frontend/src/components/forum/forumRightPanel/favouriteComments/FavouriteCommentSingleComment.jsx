import React, {useState} from 'react';
import {Button, Card, FormControl, Image, InputGroup} from "react-bootstrap";
import axios from "axios";

const FavouriteCommentSingleComment = (props) => {
    let text = props.comments.commentText;
    const [commentText, setCommentText] = useState(text)
    const [editable, setEditable] = useState(false)

    const url = "http://localhost:8080/comment_edit"

    function submit(e){
        e.preventDefault();
        axios.put(url, {
            commentText: commentText,
            commentId: props.comments.id
        }).then(r => console.log(r.data))
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
            </Card> : <Card
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
                            <Button variant="warning" className="save-note-button"
                                    onClick={(e) => editText(e)}>Edit</Button>

                            <p><Image rounded="true" fluid="true" className="addImage" src="https://cdn.icon-icons.com/icons2/2184/PNG/512/healthy_strength_strong_health_heart_icon_133538.png" alt=""/></p>
                        </Card.Text>
                    </Card.Body>
                    <Card.Footer style={{ backgroundColor: "rgb(45, 45, 45)"}}>
                    </Card.Footer>
                </Card>}
        </div>
    );
};

export default FavouriteCommentSingleComment;