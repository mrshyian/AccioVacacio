import React, {useCallback, useEffect, useState} from 'react';
import Modal from "react-bootstrap/Modal";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import {useDropzone} from "react-dropzone";
import {postDataToServerByAxiosPost, postImageToServerByAxiosPost} from "../../../../axios";

function AddNewPost(props) {
    const url = "http://localhost:8080/posts"
    const [show, setShow] = useState(true);
    const [image, setImage] = useState(false);
    const [imageToPost, setImageToPost] = useState(new FormData());
    const [postTopic, setPostTopic] = useState("");
    const [postText, setPostText] = useState("");
    const [isDisabled, setIsDisabled] = useState(true);

    useEffect(() => {
        if( postTopic !== "" && postText !== ""){
            setIsDisabled(false);
        }else{
            setIsDisabled(true)
        }
    }, [postTopic, postText]);

    const handleClose = () => {
        setToPropsModalClose();
        setShow(false);
    };

    function handle(e) {
        if (e.target.id === "topic"){
            setPostTopic(e.target.value);
        }

        if (e.target.id === "postText"){
            setPostText(e.target.value);
        }
    }

    function refreshPage() {
        window.location.reload();
    }

    const setToPropsModalClose = () => {
        props.close(false)
    }


    async function submit(e) {
        e.preventDefault();
        const data = {
            topic: postTopic,
            postText: postText
        }

        {
            !image ?
                await postDataToServerByAxiosPost(url, data, 0).then(() => refreshPage())
                :
                await postImageToServerByAxiosPost(`http://localhost:8080/image/upload/post/${postTopic}/${postText}`,
                    imageToPost, 0).then(() => console.log(imageToPost));
        }
        handleClose();
        refreshPage();
    }

    // -------------------------------------------------------------


    function Dropzone() {
        const onDrop = useCallback(acceptedFiles => {
            const file = acceptedFiles[0];
            setImage(true);

            const formData = new FormData();
            formData.append("file", file);
            setImageToPost(formData);

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
                                value={postTopic}
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
                                          value={postText}
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
                    <Button variant="warning" onClick={(e) => submit(e)} disabled={isDisabled}>
                        Save Changes
                    </Button>
                </Modal.Footer>
            </Modal>
        </>
    );
}

export default AddNewPost;