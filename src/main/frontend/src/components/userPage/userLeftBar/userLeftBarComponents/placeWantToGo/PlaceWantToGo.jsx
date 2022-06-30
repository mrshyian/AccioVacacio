import React, {useEffect, useState} from 'react';
import {Button, Card} from "react-bootstrap";
import PlaceWantToGoModal from "../../../../modals/placeWantToGo/PlaceWantToGoModal";
import axios from "axios";
import UserLeftBar from "../../UserLeftBar";


const PlaceWantToGo = (props) => {

    const [modalOpen, setModalOpen] = useState(false);
    const [places, setPlaces] = useState([])

    console.log(places)

    const getPlacesFromDB = () => {
        axios.get(`http://localhost:8080/placewanttogo`)
            .then(res => {
                setPlaces(res.data);
                console.log("data from fetch -> " + res.data)
            })
            .catch(err => {
                console.log(err)
            });
    };

    useEffect(() => {
        getPlacesFromDB();
    }, [])

    return (
        <div>
            <UserLeftBar/>
            <Card
                bg="dark"
                key={"dark"}
                text={'white'}
                className="mb-2">
                <Card.Header style={{textAlign: "center", color: "orange"}}><h2>Places want to go</h2><Button
                    variant="warning"
                    onClick={() => {setModalOpen(true);
                }}>Add place</Button></Card.Header>
                <Card.Body style={{display: "flex", flexWrap: "wrap"}}>


                    {/*{places.map((place, index) => {*/}
                    {/*    return (*/}
                    {/*        <SinglePlace country={place.country} city={place.city} key={index}/>*/}
                    {/*    )*/}
                    {/*})}*/}
                </Card.Body>
                {modalOpen && <PlaceWantToGoModal visible={modalOpen}/>}
            </Card>
        </div>
    );
};

export default PlaceWantToGo;