import React, {useEffect, useState, useCallback} from 'react';
import axios from "axios";
import {useDropzone} from "react-dropzone";
import "./AddPhotoModal.css"
import {Button, Modal} from "react-bootstrap";
import {postDataToServerByAxiosPost} from "../../../axios";

const AddPhotoModal = (props) => {
    const addImageUrl = `http://localhost:8080/${props.albumId}/${props.albumName}/image/upload`;

    useEffect(()=>{
        setShowAddPhotoModal(props.visible)
    }, [])

    const [showAddPhotoModal, setShowAddPhotoModal] = useState(false);
    const handleCloseErrorModal = () => {
        setToPropsModalClose();
        setShowAddPhotoModal(false);
    };

    const setToPropsModalClose = () => {
        props.close(false)
    }

    const onDrop = useCallback(acceptedFiles => {
        const file = acceptedFiles[0];

        const formData = new FormData();
        formData.append("file", file);
        postDataToServerByAxiosPost(addImageUrl, formData, 0)
            .then(() => {
                setToPropsModalClose();
                window.location.reload();
            })
    }, [])

    const {getRootProps, getInputProps, isDragActive} = useDropzone({onDrop})

    return (
        <Modal show={showAddPhotoModal} onHide={handleCloseErrorModal} style={{background: "rgba(0, 0, 0, 0.6)", color: "orange"}} size="xl">
            <Modal.Header closeButton style={{background: "rgb(40,40,40)"}}>
                <Modal.Title>Add photo</Modal.Title>
            </Modal.Header>
            <Modal.Body
                style={{background: "rgb(20,20,20)"}}
            >
                <div {...getRootProps()}>
                    <input {...getInputProps()} />
                    {
                        isDragActive ?
                            <p className='after-drag-photo'>Drop the files here ...</p> :
                            <p className='for-drag-photo'>Drag here some file, or click to select files</p>
                    }
                </div>
            </Modal.Body>
            <Modal.Footer  style={{background: "rgb(40,40,40)"}}>
                <Button variant="warning" onClick={handleCloseErrorModal}>
                    Close
                </Button>
            </Modal.Footer>
        </Modal>

    )
}

export default AddPhotoModal;