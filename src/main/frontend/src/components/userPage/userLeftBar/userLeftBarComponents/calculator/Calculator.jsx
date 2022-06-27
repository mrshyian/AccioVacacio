import React from 'react';
import {Card} from "react-bootstrap";

const Calculator = () => {
    return (
    <Card
        bg="dark"
        key={"dark"}
        text={'white'}
        className="mb-2 right">
        <Card.Header style={{textAlign: "center", color: "orange"}}><h2>CALCULATOR</h2></Card.Header>
        <Card.Body>
            <Card.Text>
                KALKULATOR
            </Card.Text>
        </Card.Body>
    </Card>
    );
};

export default Calculator;