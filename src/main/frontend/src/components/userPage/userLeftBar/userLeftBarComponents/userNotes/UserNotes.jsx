import React from 'react';
import {Card} from "react-bootstrap";

const UserNotes = () => {
    return (
        <Card
            bg="dark"
            key={"dark"}
            text={'white'}
            className="mb-2 right">
            <Card.Header style={{textAlign: "center", color: "orange"}}><h2>NOTE</h2></Card.Header>
            <Card.Body>
                <Card.Text>
                    NOTATNIK
                </Card.Text>
            </Card.Body>
        </Card>
    );
};

export default UserNotes;