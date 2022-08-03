import React, {useEffect, useState} from 'react';
import axios from "axios";
import {Button, Modal} from "react-bootstrap";

const DeletePlaceWantToGo = (props) => {

    const setToPropsModalClose = () => {
        props.close(false)
    }

    useEffect(()=>{
        setShowDeletePlaceModal(props.visible)
    }, [])


    const [showDeletePlaceModal, setShowDeletePlaceModal] = useState(false);
    const handleCloseLoginModal = () => {
        setToPropsModalClose();
        setShowDeletePlaceModal(false);
    };


    const sendDataToServer = () => {
        console.log(props.place.placeId)
        const url = "http://localhost:8080/placewanttogo";
        axios.put(url,{
            placeId: props.place.placeId,
        })
            .then(() => window.location.reload())
        handleCloseLoginModal()
    }


    return (
        <Modal show={showDeletePlaceModal} onHide={handleCloseLoginModal} style={{background: "rgba(0, 0, 0, 0.6)", color: "orange"}}>
            <Modal.Header closeButton style={{background: "rgb(40,40,40)"}}>
                <Modal.Title>You want to delete this place? <div style={{color: "wight"}}>({props.place.country}/{props.place.city})</div></Modal.Title>
            </Modal.Header>
            <Modal.Footer  style={{background: "rgb(40,40,40)"}}>
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

export default DeletePlaceWantToGo;