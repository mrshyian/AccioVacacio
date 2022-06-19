import React from 'react';
import "./TouristAttractionsBox.css"
import TouristSingleAttractionCard from "./TouristSingleAttractionCard";
import {Button, Card} from "react-bootstrap";

const CARD_HEADER_TITLES = ["Must see", "Your friends will be shocked", "Mom, look...", "Hurry up", "Special place for special you"];


const TouristAttractionsBox = (props) => {
    const cardHeaders = randomArrayShuffle(CARD_HEADER_TITLES);

    return (

        <div className="newsInline">
            <h1 style={{textAlign: "center", color: "orange"}}>ATTRACTIONS</h1>
            <div style={{display: "flex", flexWrap: "wrap"}}>
                {props.attractions.map((attractions, index) => {
                    return (
                        <Card
                            bg={"dark"}
                            key={"dark"}
                            text={'white'}
                            style={{width: "49%", flex: "50%"}}
                            className="mb-2"
                        >
                            <Card.Header style={{textAlign: "center", color: "orange"}}>
                                <h3>{cardHeaders[index]}</h3>
                            </Card.Header>
                            <Card.Body>
                                <TouristSingleAttractionCard key={index} attractions={attractions}/>
                            </Card.Body>
                        </Card>
                    )
                })}

            </div>
        </div>
    )

};

export default TouristAttractionsBox;


function randomArrayShuffle(array) {
    let currentIndex = array.length, temporaryValue, randomIndex;
    while (currentIndex !== 0) {
        randomIndex = Math.floor(Math.random() * currentIndex);
        currentIndex -= 1;
        temporaryValue = array[currentIndex];
        array[currentIndex] = array[randomIndex];
        array[randomIndex] = temporaryValue;
    }
    return array;
}