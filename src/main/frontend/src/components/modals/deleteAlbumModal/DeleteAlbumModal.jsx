import React, {useEffect, useState} from 'react';
import axios from "axios";
import {Button, Form, Modal} from "react-bootstrap";

const DeleteAlbumModal = (props) => {

    const setToPropsModalClose = () => {
        props.close(false)
    }

    useEffect(() => {
        setShowNewAlbumModal(props.visible)
    }, [])


    const [showNewAlbumModal, setShowNewAlbumModal] = useState(false);
    const handleCloseLoginModal = () => {
        setToPropsModalClose();
        setShowNewAlbumModal(false);
    };


    const sendDataToServer = () => {
        const url = "http://localhost:8080/albumsfromtrips";
        axios.put(url, {
                albumId: props.albumId
            })
            .then(() => window.location.reload())
        handleCloseLoginModal()

    }


    return (
        <Modal show={showNewAlbumModal} onHide={handleCloseLoginModal}
               style={{background: "rgba(0, 0, 0, 0.6)", color: "orange"}}>
            <Modal.Header closeButton style={{background: "rgb(40,40,40)"}}>
                <Modal.Title>You want to delete album?</Modal.Title>
            </Modal.Header>
            <Modal.Footer style={{background: "rgb(40,40,40)"}}>
                <Button variant="outline-secondary" onClick={handleCloseLoginModal}>
                    Close
                </Button>
                <Button variant="warning" onClick={sendDataToServer}>
                    DELETE
                </Button>
            </Modal.Footer>
        </Modal>
    );
};

export default DeleteAlbumModal;