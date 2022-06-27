import React, {useState} from 'react';
import Axios from "axios";
import "./AddNewComment.css"
import app from "../../../../App";
import {logDOM} from "@testing-library/react";
import {availiablePages} from "../../../../types";
import {Link} from "react-router-dom";
import {Button, Card} from "react-bootstrap";


function AddNewComment(props) {

    const url = "http://localhost:8080/comments"
    const [name, setName] = useState({
        name: ""
    })

    function handle(e){
        const newName = {...name}
        newName[e.target.id] = e.target.value
        setName(newName)
        console.log(newName)
    }

    function submit(e){
        refreshPage();
        e.preventDefault();
        Axios.post(url, {
            name: name.name
        }).then(r => console.log(r.data))
    }

    function refreshPage(){
        console.log("działa")
        window.location.reload();
    }

    return (
        <div className="">
            Add new comment:
            <form onSubmit={(e) => submit(e)}>
                <Card
                    key={"dark"}
                         text={'white'}

                         style={{maxWidth: '90%', margin: "10px", marginLeft: "2.5%"}}
                         className="mb-2 ">
                    <Card.Header style={{justifyContent: "space-between", color: "orange", display: "flex", backgroundColor: "rgb(35, 35, 35)"}} as="h5">Featured</Card.Header>
                    <Card.Body style={{ backgroundColor: "rgb(55, 55, 55)"}}>
                        <Card.Title>Special title treatment</Card.Title>
                        <Card.Text>
                            <textarea  style={{width: "100%", height: "100%" }} onChange={(e) => handle(e)} id="name" value={name.name} placeholder="name" type="text"/>
                        </Card.Text>
                        <Button style={{marginLeft: "93%"}} variant="outline-warning" type="submit">submit</Button>
                    </Card.Body>
                </Card>
                {/*<textarea  style={{width: "100%" }} onChange={(e) => handle(e)} id="name" value={name.name} placeholder="name" type="text"/>*/}
                {/*<Button variant="outline-warning" type="submit">submit</Button>*/}
            </form>
        </div>
    );
}

export default AddNewComment;