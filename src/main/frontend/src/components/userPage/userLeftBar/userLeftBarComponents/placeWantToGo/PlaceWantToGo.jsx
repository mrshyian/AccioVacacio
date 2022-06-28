import React, {useState} from 'react';
import {Button, Card} from "react-bootstrap";
import PlaceWantToGoModal from "../../../../modals/placeWantToGo/PlaceWantToGoModal";


const PlaceWantToGo = () => {

    const [modalOpen, setModalOpen] = useState(false);

    return (
        <Card
            bg="dark"
            key={"dark"}
            text={'white'}
            className="mb-2 right">
            <Card.Header style={{textAlign: "center", color: "orange"}}><h2>Places want to go</h2></Card.Header>
            <Card.Body>
                    <Button variant="warning" onClick={() => {
                        setModalOpen(true);
                    }}>Add place</Button>
            </Card.Body>
            {modalOpen && <PlaceWantToGoModal />}
        </Card>
    );
};

export default PlaceWantToGo;