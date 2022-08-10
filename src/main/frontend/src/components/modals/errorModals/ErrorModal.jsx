import React, {useEffect, useState} from 'react';
import {Button, Modal} from "react-bootstrap";

const ErrorModal = (props) => {

    useEffect(()=>{
        setShowErrorModal(props.visible)
    }, [])

    const [showErrorModal, setShowErrorModal] = useState(false);
    const handleCloseErrorModal = () => {
        setToPropsModalClose();
        setShowErrorModal(false);
    };

    const setToPropsModalClose = () => {
        props.close(false);
    }

    return (
        <Modal show={showErrorModal} onHide={handleCloseErrorModal} style={{background: "rgba(0, 0, 0, 0.6)", color: "orange"}}>
            <Modal.Header closeButton style={{background: "rgb(40,40,40)"}}>
                <Modal.Title>{props.errorText}</Modal.Title>
            </Modal.Header>
            <Modal.Footer  style={{background: "rgb(40,40,40)"}}>
                <Button variant="warning" onClick={handleCloseErrorModal}>
                    Close
                </Button>
            </Modal.Footer>
        </Modal>
    );
};

export default ErrorModal;