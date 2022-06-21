import React from 'react';
import {Card} from "react-bootstrap";


const InformationAboutUser = () => {
    return (
        <Card
            bg={"dark"}
            key={"dark"}
            text={'white'}
            className="mb-2"
            style={{width: "40%"}}
        >
            <Card.Header style={{textAlign: "center", color: "orange"}}><h4>About Me</h4></Card.Header>
            <Card.Body>
                <Card.Text>
                    info w user<br/>
                    info w user<br/>
                    info w user<br/>
                    info w user<br/>
                    info w user<br/>
                    info w user<br/>
                    info w user<br/>
                    info w user<br/>
                    info w user<br/>
                    info w user<br/>
                </Card.Text>
            </Card.Body>
        </Card>
    );
};

export default InformationAboutUser;