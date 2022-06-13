import React from 'react';
import oneStar from './icon/1star.png'
import twoStar from './icon/2stars.png'
import threeStar from './icon/3stars.png'
import fourStar from './icon/4stars.png'
import fiveStar from './icon/5stars.png'
import safely from './icon/safely.png'
import "./CrimeRating.css"
import {Card} from "react-bootstrap";


const CrimeRating = (props) => {
    let img;
    let returned=1;
    props.crimeRating.map((rating, index) => {
        if (props.city === props.crimeRating[index].city) {
            if (props.crimeRating[index].index < 21) {
                img = fiveStar;
            } else if (props.crimeRating[index].index < 41) {
                img = fourStar;
            } else if (props.crimeRating[index].index < 61) {
                img = threeStar;
            } else if (props.crimeRating[index].index < 81) {
                img = twoStar;
            } else {
                img = oneStar;
            }
            returned =
                <div style={{margin: "5px"}}>
                <Card
                    bg={"dark"}
                    key={"dark"}
                    text={'white'}
                    style={{ width: '20rem' }}
                    className="mb-2"
                >
                    <Card.Header style={{textAlign: "center", color: "orange"}}><h2>Crime Rating</h2></Card.Header>
                    <Card.Body>
                        <Card.Text>
                            <div style={{textAlign: "center"}}>
                            <img className="img-for-crime-rating"
                                 src={img}
                                 alt="random image"/>
                            </div>
                        </Card.Text>
                    </Card.Body>
                </Card>
                </div>
        }
    })

    if (returned !== 1) {
        return (
            returned
        );
    } else {
        return (
            <div style={{margin: "5px"}}>
            <Card
                bg={"dark"}
                key={"dark"}
                text={'white'}
                style={{ width: '20rem' }}
                className="mb-2"
            >
                <Card.Header style={{textAlign: "center", color: "orange"}}><h2>Crime Rating</h2></Card.Header>
                <Card.Body>
                    <Card.Text>
                        <div style={{textAlign: "center"}}>
                        <img className="img-for-crime-rating"
                             src={safely}
                             alt="random image"/>
                        </div>
                    </Card.Text>
                </Card.Body>
            </Card>
            </div>
        );
    }

}

export default CrimeRating;