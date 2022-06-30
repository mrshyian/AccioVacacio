

import axios from "axios";
import {Button, Form, Modal} from "react-bootstrap";
import React, {useEffect, useState} from "react";

const AddNewAlbumModal = (props) => {
    useEffect(()=>{
        setShowNewAlbumModal(props.visible)
    }, [])
    const [country, setCountry] = useState("")
    const [city, setCity] = useState("")
    const [tripDate, setTripDate] = useState("")
    const [albumName, setAlbumName] = useState("")
    const [tripDescription, setTripDescription] = useState("")


    const [showNewAlbumModal, setShowNewAlbumModal] = useState(false);
    const handleCloseLoginModal = () => setShowNewAlbumModal(false);


    const sendDataToServer = () => {
        window.location.reload();
        const url = "http://localhost:8080/albumsfromtrips";
        axios.post(url,{
            country: country,
            city: city,
            tripDate: tripDate,
            albumName: albumName,
            tripDescription: tripDescription
        })
            .then(res=>{
                console.log(res);
            })
        handleCloseLoginModal()

    }


    return (
        <Modal show={showNewAlbumModal} onHide={handleCloseLoginModal} style={{background: "rgba(0, 0, 0, 0.6)", color: "orange"}}>
            <Modal.Header closeButton style={{background: "rgb(40,40,40)"}}>
                <Modal.Title>Add new album</Modal.Title>
            </Modal.Header>
            <Modal.Body style={{background: "rgb(20,20,20)"}}>
                <Form style={{background: "rgb(20,20,20)"}}>
                    <Form.Group
                        className="mb-3"
                    >
                        <Form.Label style={{color: "orange"}}>Album name</Form.Label>
                        <Form.Control
                            value={albumName}
                            onChange={e=> setAlbumName(e.target.value)}
                            type="text"
                        />
                    </Form.Group>
                    {/*-------------------------------------*/}
                    <Form.Group className="mb-3">
                        <Form.Label style={{color: "orange"}}>Country</Form.Label>
                        <Form.Control
                            value={country}
                            onChange={e=> setCountry(e.target.value)}
                            type="text"
                            autoFocus
                        />
                    </Form.Group>
                    {/*-------------------------------------*/}
                    <Form.Group
                        className="mb-3"
                    >
                        <Form.Label style={{color: "orange"}}>City</Form.Label>
                        <Form.Control
                            value={city}
                            onChange={e=> setCity(e.target.value)}
                            type="text"
                        />
                    </Form.Group>
                    {/*-------------------------------------*/}<Form.Group
                        className="mb-3"
                    >
                        <Form.Label style={{color: "orange"}}>Trip date</Form.Label>
                        <Form.Control
                            value={tripDate}
                            onChange={e=> setTripDate(e.target.value)}
                            type="text"
                        />
                    </Form.Group>
                    {/*-------------------------------------*/}
                    <Form.Group
                        className="mb-3"
                    >
                        <Form.Label style={{color: "orange"}}>Trip description</Form.Label>
                        <Form.Control
                            value={tripDescription}
                            onChange={e=> setTripDescription(e.target.value)}
                            type="text"
                        />
                    </Form.Group>
                </Form>
            </Modal.Body>
            <Modal.Footer  style={{background: "rgb(40,40,40)"}}>
                <Button variant="outline-secondary" onClick={handleCloseLoginModal}>
                    Close
                </Button>
                <Button variant="outline-warning" onClick={sendDataToServer}>
                    Create
                </Button>
            </Modal.Footer>
        </Modal>
    );
};

export default AddNewAlbumModal;