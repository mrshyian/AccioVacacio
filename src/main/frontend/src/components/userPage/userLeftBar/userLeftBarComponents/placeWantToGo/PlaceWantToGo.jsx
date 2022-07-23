import React, {useEffect, useState} from 'react';
import {Button, Card} from "react-bootstrap";
import axios from "axios";
import UserLeftBar from "../../UserLeftBar";
import SinglePlace from "./SinglePlace";
import "./PlaceWantToGo.css"
import PlaceWantToGoModal from "../../../../modals/placeWantToGoModal/PlaceWantToGoModal";
import MustBeLogIn from "../../../../mustBeLogIn/MustBeLogIn";


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
        if (sessionStorage.getItem("userId") !== null){
            getPlacesFromDB();
        }
    }, [])


    if (sessionStorage.getItem("userId") !== null) {
        return (
            <div>
                <UserLeftBar/>
                <Card
                    bg="dark"
                    text={'white'}
                    className="mb-2">
                    <Card.Header style={{textAlign: "center", color: "orange"}}><h2>Places want to go</h2><Button
                        variant="warning"
                        onClick={() => {
                            setModalOpen(true);
                        }}>Add place</Button></Card.Header>
                    <Card.Body>
                        <div className="flex-box-for-place-want-to-go">
                            {places.map((place, index) => {
                                return (
                                    <SinglePlace place={place} imageUrl={place.imagesUrl[randomNumberInRange()]}
                                                 key={index}/>
                                )
                            })}
                        </div>
                    </Card.Body>
                    {modalOpen && <PlaceWantToGoModal visible={modalOpen}/>}
                </Card>
            </div>
        );
    } else {
        return <MustBeLogIn/>;
    }
};

export default PlaceWantToGo;