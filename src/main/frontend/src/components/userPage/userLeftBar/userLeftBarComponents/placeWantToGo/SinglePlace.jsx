import React, {useState} from 'react';
import {Button, Card} from "react-bootstrap";
import "./PlaceWantToGo.css"
import {useNavigate} from "react-router-dom";
import {FaTimes} from "react-icons/fa";
import DeletePlaceWantToGo from "../../../../modals/deletePlaceWantToGo/DeletePlaceWantToGo";

const SinglePlace = (props) => {
    const navigate = useNavigate();
    const [modalOpen, setModalOpen] = useState(false);

    return (
        <div className="btn-on-image single-place-card">
            <Button onClick={() => {
                setModalOpen(true)
            }} variant="dark" className="delete-place-btn">{<FaTimes/>}</Button>
            <Card
                bg="dark"
                text={'white'}
                onClick={()=> navigate("/SearchCity", {state: {
                        city: props.place.city,
                        country: props.place.country
                    }})}
            >
                <Card.Img className="img-for-single-place-want-to-go" variant="top" src={props.imageUrl} />
                <Card.Body className="single-place-body">
                    <Card.Title>
                        {props.place.country} / {props.place.city}
                    </Card.Title>
                </Card.Body>
            </Card>
            {modalOpen && <DeletePlaceWantToGo place={props.place} visible={modalOpen} close={setModalOpen}/>}
        </div>

    );
};

export default SinglePlace;