import React from 'react';
import {Button, Card} from "react-bootstrap";
import "./SingleAlbum.css"

const SingleAlbum = (props) => {
    return (
        <Card
            bg="dark"
            key={"dark"}
            text={'white'}
            className="album-card"
        >
            <Card.Img className="album-image" variant="top" src={props.urlForImage} />
            <Card.Body>
                <Card.Title><div>{props.albumName}</div><div className="album-date-and-place">{props.city}/{props.country} - {props.tripDate}</div></Card.Title>
                <Card.Text>
                    {props.tripDescription}
                </Card.Text>
                <Button variant="outline-warning">Go to album</Button>
            </Card.Body>
        </Card>
    );
};

export default SingleAlbum;