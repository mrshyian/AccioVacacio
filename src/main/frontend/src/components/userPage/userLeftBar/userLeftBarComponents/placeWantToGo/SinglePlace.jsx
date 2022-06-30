import React from 'react';
import {Card} from "react-bootstrap";

const SinglePlace = (props) => {
    // console.log(props.placeUrl)
    return (
        <Card
            style={{ width: '18rem', marginLeft: "5%" }}
            bg="dark"
            key={"dark"}
            text={'white'}>
            <Card.Img variant="top" src={props.imageUrl} />
            <Card.Body>
                <Card.Title>
                    {props.country} {props.city}
                </Card.Title>
            </Card.Body>
        </Card>
    );
};

export default SinglePlace;