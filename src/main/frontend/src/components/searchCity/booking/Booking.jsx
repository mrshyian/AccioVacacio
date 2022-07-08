import React, {useEffect, useState} from 'react';
import {Button, Card} from "react-bootstrap";
import axios from "axios";
import star from "../../../images/star.png"

const Booking = (props) => {

    const [bookingDetails, setBookingDetails] = useState([]);

    const fetchBookingDetails = () => {
        axios.get(`http://localhost:8080/booking/${props.city}/${props.country}`)
            .then(res => {
                setBookingDetails(res.data);
            })
            .catch(err => {
                console.log(err)
            });
    };

    useEffect(() => {
        fetchBookingDetails();
    }, [])
    return (
        <div className="newsInline1" style={{width: "30%"}}>
            <h1 style={{textAlign: "center", color: "orange"}}>AIRPORTS</h1>
            <div style={{display: "flex", marginLeft: "10%"}}>
                <Card
                    bg={"dark"}
                    key={"dark"}
                    text={'white'}
                    style={{width: '20rem'}}
                    className="mb-2"
                >
                    <Card.Header style={{textAlign: "center", color: "orange"}}><h2>{bookingDetails.name}</h2>
                    </Card.Header>
                    <Card.Body>
                        <Card.Text>
                            <p>{bookingDetails.rating} / 10 <img style={{width: 20}} src={star}/></p>
                            <Button variant="outline-warning" href={bookingDetails.link}>Book</Button>
                        </Card.Text>
                    </Card.Body>
                </Card>
            </div>
        </div>
    );
};

export default Booking;