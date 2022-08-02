import React from 'react';
import {Card} from "react-bootstrap";

const CountryCounter = () => {
    return (
        <Card
            bg={"dark"}
            key={"dark"}
            text={'white'}
            className="mb-2"
            style={{width: "50%", marginLeft: "auto", marginRight: "auto", border: "1px solid orange"}}
        >
            <Card.Header style={{textAlign: "center", color: "orange"}}><h4>Country Counter</h4></Card.Header>
            <Card.Body>
                <Card.Text>
                    <a style={{color: "orange"}} href="/userpage"><br/><div>Poland/Poznan</div></a>
                    <a style={{color: "orange"}} href="/userpage"><br/><div>Rome/Italy</div></a>
                    <a style={{color: "orange"}} href="/userpage"><br/><div>Barcelona/Spain</div></a>
                </Card.Text>
            </Card.Body>
        </Card>
    );
};

export default CountryCounter;