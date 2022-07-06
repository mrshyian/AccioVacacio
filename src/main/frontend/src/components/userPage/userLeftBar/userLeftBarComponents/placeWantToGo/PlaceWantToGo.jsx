import React, {useEffect, useState} from 'react';
import {Button, Card} from "react-bootstrap";
import axios from "axios";
import UserLeftBar from "../../UserLeftBar";
import SinglePlace from "./SinglePlace";
import "./PlaceWantToGo.css"
import PlaceWantToGoModal from "../../../../modals/placeWantToGoModal/PlaceWantToGoModal";


const PlaceWantToGo = () => {

    const [modalOpen, setModalOpen] = useState(false);
    const [places, setPlaces] = useState([]);

    const getPlacesFromDB = () => {
        axios.get(`http://localhost:8080/placewanttogo`)
            .then(res => {
                setPlaces(res.data);
            })
            .catch(err => {
                console.log(err)
            });
    };

    function randomNumberInRange() {
        return Math.floor(Math.random() * (2 + 1));
    }

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
                <Card.Body>
                    <div className="flex-box-for-place-want-to-go">
                        {places.map((place, index) => {
                            return (
                                <SinglePlace country={place.country} city={place.city} imageUrl={place.imagesUrl[randomNumberInRange()]} key={index}/>
                            )
                        })}
                    </div>
                </Card.Body>
                {modalOpen && <PlaceWantToGoModal visible={modalOpen}/>}
            </Card>
        </div>
    );
};

export default PlaceWantToGo;