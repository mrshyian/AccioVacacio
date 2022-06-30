import React from 'react';
import {Card} from "react-bootstrap";

const CountryCounter = () => {
    return (
        <Card
            bg={"dark"}
            key={"dark"}
            text={'white'}
            className="mb-2"
            style={{width: "100%"}}
        >
            <Card.Header style={{textAlign: "center", color: "orange"}}><h4>Country Counter</h4></Card.Header>
            <Card.Body>
                <Card.Text>
                    Cos tam
                </Card.Text>
            </Card.Body>
        </Card>
    );
};

export default CountryCounter;