import React from 'react';
import {Card} from "react-bootstrap";


const MustBeLogIn = () => {

    return (
        <Card
            bg={"dark"}
            key={"dark"}
            text={'warning'}
            className="mb-2 bg-opacity"
            style={{marginTop: "10%", padding: "30px"}}
        >
            <Card.Body >
                <Card.Text style={{background: "#212121", height: "300px"}}>
                    <h1 style={{padding: "10%"}}>THIS OPTION ONLY FOR REGISTERED USERS</h1>
                </Card.Text>
            </Card.Body>
        </Card>
    );
};

export default MustBeLogIn;