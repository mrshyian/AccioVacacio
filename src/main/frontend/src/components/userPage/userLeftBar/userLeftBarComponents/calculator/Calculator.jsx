import React from 'react';
import {Card} from "react-bootstrap";
import UserLeftBar from "../../UserLeftBar";
import "./Calculator.css"
import MustBeLogIn from "../../../../mustBeLogIn/MustBeLogIn";

const Calculator = () => {
    if (sessionStorage.getItem("userId") !== null) {
        return (
            <div>
                <UserLeftBar/>
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
            </div>
        );
    } else {
        return <MustBeLogIn/>;
    }

};

export default Calculator;