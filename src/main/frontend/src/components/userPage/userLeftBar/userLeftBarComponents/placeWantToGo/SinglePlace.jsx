import React from 'react';
import {Card} from "react-bootstrap";
import "./PlaceWantToGo.css"
import {useNavigate} from "react-router-dom";

const SinglePlace = (props) => {
    const navigate = useNavigate();

    return (
        <Card
            className="single-place-card"
            bg="dark"
            key={"dark"}
            text={'white'}
            onClick={()=> navigate("/SearchCity", {state: {
                    city: props.city,
                    country: props.country
                }})}
        >
            <Card.Img className="img-for-single-place-want-to-go" variant="top" src={props.imageUrl} />
            <Card.Body className="single-place-body">
                <Card.Title>
                    {props.country} / {props.city}
                </Card.Title>
            </Card.Body>
        </Card>
    );
};

export default SinglePlace;