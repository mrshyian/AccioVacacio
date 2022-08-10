import React, {useEffect, useState} from 'react';
import {Button, Card} from "react-bootstrap";
import axios from "axios";
import star from "../../../images/star.png"

const Booking = (props) => {

    const [bookingDetails, setBookingDetails] = useState([]);

    const fetchBookingDetails = () => {
        axios.get(`http://localhost:8080/booking/${props.city}/${props.country}`)
            .then(res => {
                if (res.status === 200)
                {
                    setBookingDetails(res.data);
                }
            })
            .catch(err => {
                console.log(err)
            });
    };

    useEffect(() => {
        fetchBookingDetails();
    }, []);

    return (
        <div>
            { bookingDetails ?
                <div className="newsInline1" style={{width: "90%", height: "50%"}}>
                    <h1 style={{textAlign: "center", color: "orange"}}>Booking</h1>
                    <div style={{display: "flex", marginLeft: "10%"}}>
                        <Card
                            bg={"dark"}
                            key={"booking-dark"}
                            text={'white'}
                            style={{width: '20rem', borderColor: "orange" }}
                            className="mb-2"
                        >
                            <Card.Header style={{textAlign: "center", color: "orange"}}><h2>{bookingDetails.name}</h2>
                            </Card.Header>
                            <Card.Body>
                                <Card.Text>
                                    <br/><div>{bookingDetails.rating} / 10 <img style={{width: 20}} src={star}/></div>
                                    <Button variant="outline-warning" href={bookingDetails.link}>Book</Button>
                                </Card.Text>
                            </Card.Body>
                        </Card>
                    </div>
                </div> :

                <div className="newsInline1" style={{width: "90%", height: "50%"}}>
                    <h1 style={{textAlign: "center", color: "orange"}}>Booking</h1>
                    <div style={{display: "flex", marginLeft: "10%"}}>
                        <Card
                            bg={"dark"}
                            key={"booking-dark"}
                            text={'white'}
                            style={{width: '20rem', borderColor: "orange" }}
                            className="mb-2"
                        >
                            <Card.Header style={{textAlign: "center", color: "orange", width: "96%"}}><h2>{props.city}</h2>
                            </Card.Header>
                            <Card.Body>
                                <Card.Text>
                                    <br/><div> ?? / 10 <img style={{width: 20}} src={star}/></div>
                                    <div>Smth went wrong...</div>
                                </Card.Text>
                            </Card.Body>
                        </Card>
                    </div>
                </div>

            }
        </div>
    );
};

export default Booking;