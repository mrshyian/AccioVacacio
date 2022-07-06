import React, {useState} from 'react';
import Axios from "axios";
import "./AddNewComment.css"
import {Button, Card} from "react-bootstrap";


function AddNewComment(props) {

    const url = "http://localhost:8080/comments"
    const [name, setName] = useState({
        name: ""
    })

    function handle(e) {
        const newName = {...name}
        newName[e.target.id] = e.target.value
        setName(newName)
    }

    function submit(e) {
        refreshPage();
        e.preventDefault();
        Axios.post(url, {
            name: name.name,
            postId: props.postId
        }).then(r => console.log(r.data))
    }

    function refreshPage() {
        window.location.reload();
    }

    return (
        <div>
            <form onSubmit={(e) => submit(e)}>
                <Card
                    key={"dark"}
                    text={'white'}
                    className="mb-2 add-comment-card">
                    <Card.Body className="add-comment-card-body">
                        <Card.Text>
                            <textarea className="add-comment-textarea" onChange={(e) => handle(e)} id="name" value={name.name}
                                      placeholder="Comment text" type="text"/>
                        </Card.Text>
                        <div style={{textAlign: "right"}}>
                            <Button variant="outline-warning" type="submit">Add comment</Button>
                        </div>
                    </Card.Body>
                </Card>
            </form>
        </div>
    );
}

export default AddNewComment;