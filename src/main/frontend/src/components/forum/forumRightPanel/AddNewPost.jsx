import React, {useState} from 'react';
import Axios from "axios";
import Modal from "react-bootstrap/Modal";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";

function AddNewPost() {
    const [show, setShow] = useState(true);

    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);
    const url = "http://localhost:8080/posts"
    const [data, setData] = useState({
        topic: "",
        postText: ""
    })

    function handle(e){
        const newData = {...data}
        newData[e.target.id] = e.target.value
        setData(newData)
        console.log(newData)
    }

    function refreshPage(){
        console.log("działa")
        window.location.reload();
    }

    function submit(e){
        handleClose();
        refreshPage();
        e.preventDefault();
        console.log("submit działa")
        Axios.post(url, {
            topic: data.topic,
            postText: data.postText
        }).then(r => console.log(r.data))
    }

    return (
        <>
            <Modal show={show} onHide={handleClose} style={{background: "rgba(0, 0, 0, 0.6)", color: "orange"}}>
                <Modal.Header closeButton style={{background: "rgb(40,40,40)"}} >
                    <Modal.Title >Add Post</Modal.Title>
                </Modal.Header>
                <Modal.Body style={{background: "rgb(20,20,20)"}}>
                    <Form style={{background: "rgb(20,20,20)"}}>
                        <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                            <Form.Label style={{color: "orange"}}>Topic</Form.Label>
                            <Form.Control
                                type="text"
                                id="topic"
                                placeholder="Topic"
                                autoFocus
                                value={data.topic}
                                onChange={(e) => handle(e)}
                            />
                        </Form.Group>
                        <Form.Group
                            className="mb-2"
                            controlId="exampleForm.ControlTextarea1"
                        >
                            <Form.Label style={{color: "orange"}}>Post Message</Form.Label>
                            <Form.Control as="textarea" rows={3}
                                          type="text"
                                          id="postText"
                                          autoFocus
                                          value={data.postText}
                                          onChange={(e) => handle(e)}

                            />
                        </Form.Group>
                    </Form>
                </Modal.Body>
                <Modal.Footer style={{background: "rgb(40,40,40)"}}>
                    <Button variant="secondary" onClick={handleClose}>
                        Close
                    </Button>
                    <Button variant="primary" onClick={ (e) => submit(e)}>
                        Save Changes
                    </Button>
                </Modal.Footer>
            </Modal>
        </>
        // <div>
        //     <form onSubmit={(e) => submit(e)}>
        //         <input onChange={(e) => handle(e)} id="topic" value={data.topic} placeholder="topic" type="text"/>
        //         <input onChange={(e) => handle(e)} id="postText" value={data.postText} placeholder="text" type="text"/>
        //         <button type="submit">submit</button>
        //     </form>
        // </div>
    );
}
export default AddNewPost;