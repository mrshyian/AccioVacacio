import React from 'react';
import {Card} from "react-bootstrap";

const AlbumsFromTrips = () => {
    return (
        <Card
            bg="dark"
            key={"dark"}
            text={'white'}
            className="mb-2 right">
            <Card.Header style={{textAlign: "center", color: "orange"}}><h2>Photo albums</h2></Card.Header>
            <Card.Body>
                <Card.Text>
                    ALBUM ZE ZDJĘCIAMI
                </Card.Text>
            </Card.Body>
        </Card>
    );
};

export default AlbumsFromTrips;