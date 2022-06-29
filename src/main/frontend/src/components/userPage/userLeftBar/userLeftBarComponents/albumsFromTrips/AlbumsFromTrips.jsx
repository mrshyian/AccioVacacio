import React from 'react';
import {Card} from "react-bootstrap";
import UserLeftBar from "../../UserLeftBar";

const AlbumsFromTrips = () => {
    return (
        <div>
            <UserLeftBar/>
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
        </div>
    );
};

export default AlbumsFromTrips;