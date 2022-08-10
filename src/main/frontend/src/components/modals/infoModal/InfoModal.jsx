import React, {useEffect, useState} from 'react';
import {Button, Modal} from "react-bootstrap";

const InfoModal = (props) => {

    const [showInfoModal, setShowInfoModal] = useState(false);

    useEffect(()=>{
        setShowInfoModal(props.visible);
    }, []);

    const handleCloseInfoModal = () => {
        setShowInfoModal(false);
    };


    return (
        <Modal show={showInfoModal} onHide={handleCloseInfoModal} style={{background: "rgba(0, 0, 0, 0.6)", color: "orange"}}>
            <Modal.Header closeButton style={{background: "rgb(40,40,40)"}}>
                <Modal.Title>{props.info}</Modal.Title>
            </Modal.Header>
            <Modal.Footer  style={{background: "rgb(40,40,40)"}}>
                <Button variant="warning" onClick={handleCloseInfoModal}>
                    Close
                </Button>
            </Modal.Footer>
        </Modal>
    );
};

export default InfoModal;