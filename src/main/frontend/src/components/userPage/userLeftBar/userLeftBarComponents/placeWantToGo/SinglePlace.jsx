import React from 'react';
import {Card} from "react-bootstrap";
import "./PlaceWantToGo.css"

const SinglePlace = (props) => {

    return (
        <Card
            className="single-place-card"
            bg="dark"
            key={"dark"}
            text={'white'}>
            <Card.Img className="img-for-single-place-want-to-go" variant="top" src={props.imageUrl} />
            <Card.Body>
                <Card.Title>
                    {props.country} / {props.city}
                </Card.Title>
            </Card.Body>
        </Card>
    );
};

export default SinglePlace;