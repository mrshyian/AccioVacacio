import React, {useEffect, useState} from 'react';
import {Button, Modal} from "react-bootstrap";

const ErrorModal = (props) => {

    useEffect(()=>{
        setShowNewAlbumModal(props.visible)
    }, [])

    const [showNewAlbumModal, setShowNewAlbumModal] = useState(false);
    const handleCloseLoginModal = () => setShowNewAlbumModal(false);

    return (
        <Modal show={showNewAlbumModal} onHide={handleCloseLoginModal} style={{background: "rgba(0, 0, 0, 0.6)", color: "orange"}}>
            <Modal.Header closeButton style={{background: "rgb(40,40,40)"}}>
                <Modal.Title>{props.errorText}</Modal.Title>
            </Modal.Header>
            <Modal.Footer  style={{background: "rgb(40,40,40)"}}>
                <Button variant="warning" onClick={handleCloseLoginModal}>
                    Close
                </Button>
            </Modal.Footer>
        </Modal>
    );
};

export default ErrorModal;