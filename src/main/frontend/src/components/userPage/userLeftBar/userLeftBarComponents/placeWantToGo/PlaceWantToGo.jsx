import React, {useState} from 'react';
import {Button, Card} from "react-bootstrap";
import PlaceWantToGoModal from "../../../../modals/placeWantToGo/PlaceWantToGoModal";
import SinglePlace from "./SinglePlace";


const PlaceWantToGo = () => {

    const [modalOpen, setModalOpen] = useState(false);
    const [places, setPlaces] = useState([])



    return (
        <Card
            bg="dark"
            key={"dark"}
            text={'white'}
            className="mb-2">
            <Card.Header style={{textAlign: "center", color: "orange"}}><h2>Places want to go</h2><Button variant="warning" onClick={() => {
                setModalOpen(true);
            }}>Add place</Button></Card.Header>
            <Card.Body style={{display: "flex", flexWrap: "wrap"}}>
                    <SinglePlace country={"Poland"} city={"Warsaw"}/>
                    <SinglePlace country={"Ukraine"} city={"Kyiv"}/>
                    <SinglePlace country={"France"} city={"Paris"}/>
                    <SinglePlace country={"Germany"} city={"Berlin"}/>

                {/*{places.map((place, index) => {*/}
                {/*    return (*/}
                {/*        <SinglePlace country={place.country} city={place.city} key={index}/>*/}
                {/*    )*/}
                {/*})}*/}
            </Card.Body>
            {modalOpen && <PlaceWantToGoModal visible={modalOpen}/>}
        </Card>
    );
};

export default PlaceWantToGo;