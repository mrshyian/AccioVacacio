
import {Button, Modal, Form} from "react-bootstrap";
import axios from "axios";
import {useEffect, useState} from "react";


const PlaceWantToGoModal = (props) => {
    useEffect(()=>{
        setShowLoginModal(props.visible)
    }, [])
    const [country, setCountry] = useState("")
    const [city, setCity] = useState("")


    const [showLoginModal, setShowLoginModal] = useState(false);
    const handleCloseLoginModal = () => setShowLoginModal(false);


    const sendDataToServer = () => {
        const url = "http://localhost:8080/placewanttogo";
        axios.post(url,{
            country: country,
            city: city
        })
            .then(res=>{
                console.log(res);
            })
        handleCloseLoginModal()

    }


    return (
        <Modal show={showLoginModal} onHide={handleCloseLoginModal} style={{background: "rgba(0, 0, 0, 0.6)", color: "orange"}}>
            <Modal.Header closeButton style={{background: "rgb(40,40,40)"}}>
                <Modal.Title>New place</Modal.Title>
            </Modal.Header>
            <Modal.Body style={{background: "rgb(20,20,20)"}}>
                <Form style={{background: "rgb(20,20,20)"}}>
                    <Form.Group className="mb-3">
                        <Form.Label style={{color: "orange"}}>Country</Form.Label>
                        <Form.Control
                            value={country}
                            onChange={e=> setCountry(e.target.value)}
                            type="text"
                            placeholder="for example Poland"
                            autoFocus
                        />
                    </Form.Group>
                    <Form.Group
                        className="mb-3"
                    >
                        <Form.Label style={{color: "orange"}}>City</Form.Label>
                        <Form.Control
                            value={city}
                            onChange={e=> setCity(e.target.value)}
                            type="text"
                            placeholder="for example Warsaw"/>
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

export default PlaceWantToGoModal;