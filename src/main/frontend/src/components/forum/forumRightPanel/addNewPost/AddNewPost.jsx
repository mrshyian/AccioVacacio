import React, {useCallback, useState} from 'react';
import Axios from "axios";
import Modal from "react-bootstrap/Modal";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import axios from "axios";
import {useDropzone} from "react-dropzone";

function AddNewPost(props) {
    const [show, setShow] = useState(true);
    const [image, setImage] = useState(false);
    const [dataa, setDataa] = useState(new FormData());



    const handleClose = () => {
        setToPropsModalClose();
        setShow(false);
    };
    const handleShow = () => setShow(true);

    const url = "http://localhost:8080/posts"
    const [data, setData] = useState({
        topic: "",
        postText: ""
    })

    function handle(e) {
        const newData = {...data}
        newData[e.target.id] = e.target.value
        setData(newData)
    }

    function refreshPage() {
        window.location.reload();
    }

    const setToPropsModalClose = () => {
        props.close(false)
    }


    function submit(e) {
        handleClose();
        e.preventDefault();
        console.log(dataa)

        {
            !image ?
                Axios.post(url, {
                    topic: data.topic,
                    postText: data.postText
                }).then(() => refreshPage())
                :
            axios.post(`http://localhost:8080/image/upload/post/${data.topic}/${data.postText}`,
                dataa,
                {
                    headers: {
                        "Content-Type": "multipart/form-data"
                    }
                }).then(() => {
                console.log("file upload successfully");
            }).catch(err => {
                console.log(err);
            });
        }
    }

    // -------------------------------------------------------------


    function Dropzone() {
        const onDrop = useCallback(acceptedFiles => {
            const file = acceptedFiles[0];
            setImage(true);

            console.log(acceptedFiles);

            const formData = new FormData();
            formData.append("file", file);
            setDataa(formData);

        }, []);
        const {getRootProps, getInputProps, isDragActive} = useDropzone({onDrop})

        return (
            <div {...getRootProps()}>
                <input {...getInputProps()} />
                {
                    isDragActive ?
                        <p className='after-drag'>Drop the files here ...</p> :
                        <p className='for-drag'>Drag 'n' drop some files here, or click to select files</p>
                }
            </div>
        )
    }

    //--------------------------------------------------------------
    return (
        <>
            <Modal show={show} onHide={handleClose} style={{background: "rgba(0, 0, 0, 0.6)", color: "orange"}}>
                <Modal.Header closeButton style={{background: "rgb(40,40,40)"}}>
                    <Modal.Title>Add Post</Modal.Title>
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
                                          placeholder="Post text"
                                          style={{marginLeft: -4}}
                                          value={data.postText}
                                          onChange={(e) => handle(e)}
                            />
                        </Form.Group>
                        <div>
                            <Dropzone/>
                        </div>
                    </Form>
                </Modal.Body>
                <Modal.Footer style={{background: "rgb(40,40,40)"}}>
                    <Button variant="secondary" onClick={handleClose}>
                        Close
                    </Button>
                    <Button variant="warning" onClick={(e) => submit(e)}>
                        Save Changes
                    </Button>
                </Modal.Footer>
            </Modal>
        </>
    );
}
export default AddNewPost;