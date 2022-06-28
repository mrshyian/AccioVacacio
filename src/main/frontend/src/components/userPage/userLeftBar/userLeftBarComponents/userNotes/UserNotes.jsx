import React, {useState} from 'react';
import {Button, Card, Form, FormControl, InputGroup} from "react-bootstrap";
import {FaAlignCenter, FaAlignLeft, FaAlignRight} from 'react-icons/fa';
import "./UserNotes.css"
import axios from "axios";
import Axios from "axios";


let text = "";

function fetchNoteText() {
    axios.get(`http://localhost:8080/notes`)
        .then(res => {
            text = res.data;
        })
        .catch(err => {
            console.log(err)
        });
}


const UserNotes = () => {

    fetchNoteText();
    const [noteText, setNoteText] = useState(text)


    function textInCenter() {
        document.getElementById("note-input-id").classList.remove("text-in-left-side")
        document.getElementById("note-input-id").classList.remove("text-in-right-side")

        document.getElementById("note-input-id").classList.add("text-in-center")
    }

    function textInLeftSide() {
        document.getElementById("note-input-id").classList.remove("text-in-center")
        document.getElementById("note-input-id").classList.remove("text-in-right-side")

        document.getElementById("note-input-id").classList.add("text-in-left-side")
    }

    function textInRightSide() {
        document.getElementById("note-input-id").classList.remove("text-in-center")
        document.getElementById("note-input-id").classList.remove("text-in-left-side")

        document.getElementById("note-input-id").classList.add("text-in-right-side")
    }


    const handleChange = event => {

        document.getElementById("note-input-id").classList.remove("note-text-size-16")
        document.getElementById("note-input-id").classList.remove("note-text-size-20")
        document.getElementById("note-input-id").classList.remove("note-text-size-24")
        document.getElementById("note-input-id").classList.remove("note-text-size-28")
        document.getElementById("note-input-id").classList.remove("note-text-size-32")

        document.getElementById("note-input-id").classList.add(event.target.value)
    };


    function submit(e) {
        e.preventDefault();
        Axios.post(`http://localhost:8080/notes`, {
            noteText: noteText
        }).then(r => {
            console.log(r.data);
        })
    }


    return (
        <Card
            bg="dark"
            key={"dark"}
            text={'white'}
            className="mb-2 right">
            <Card.Header style={{textAlign: "center", color: "orange"}}><h2>NOTE</h2></Card.Header>
            <Card.Body>
                <Card.Text>
                    <Card
                        bg="dark"
                        key={"dark"}>
                        <Card.Header style={{display: "flex"}}>
                            <Button className="text-align-button" variant="outline-warning"
                                    onClick={textInLeftSide}><FaAlignLeft/></Button>
                            <Button className="text-align-button" variant="outline-warning"
                                    onClick={textInCenter}><FaAlignCenter/></Button>
                            <Button className="text-align-button" variant="outline-warning"
                                    onClick={textInRightSide}><FaAlignRight/></Button>
                            <Form.Select onChange={handleChange} className="note-text-size-select">
                                <option value="note-text-size-16">16</option>
                                <option value="note-text-size-20">20</option>
                                <option value="note-text-size-24">24</option>
                                <option value="note-text-size-28">28</option>
                                <option value="note-text-size-32">32</option>
                            </Form.Select>
                            <Button variant="warning" className="save-note-button"
                                    onClick={(e) => submit(e)}>Save</Button>
                        </Card.Header>
                        </Card>
                    <InputGroup>
                        <FormControl
                            id="note-input-id"
                            className="note-input"
                            as="textarea"
                            aria-label="With textarea"
                            value={noteText}
                            onChange={(e) => setNoteText(e.target.value)}/>
                    </InputGroup>
                </Card.Text>
            </Card.Body>
        </Card>
    );
};

export default UserNotes;