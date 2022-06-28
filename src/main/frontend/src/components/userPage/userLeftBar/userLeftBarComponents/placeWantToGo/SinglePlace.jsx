import React from 'react';
import {Card} from "react-bootstrap";

const SinglePlace = (props) => {
    return (
        <Card
            style={{ width: '18rem', marginLeft: "5%" }}
            bg="dark"
            key={"dark"}
            text={'white'}>
            <Card.Img variant="top" src="https://ocdn.eu/images/pulscms/ODU7MDA_/9a730a9c48bef81424937f7fabb2cc6e.jpg" />
            <Card.Body>
                <Card.Title>
                    {props.country} {props.city}
                </Card.Title>
            </Card.Body>
        </Card>
    );
};

export default SinglePlace;