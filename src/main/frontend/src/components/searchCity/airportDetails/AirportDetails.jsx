import React, {useEffect, useState} from 'react';
import axios from "axios";
import {Button, Card} from "react-bootstrap";
import plane from "../../../images/plane.png"

const AirportDetails = (props) => {

    const [airportDetail, setAirportDetail] = useState([]);

    const fetchAirportDetails = () => {
        console.log(props.iata)
        axios.get(`http://localhost:8080/airport-details/${props.iata}/${props.city}/${props.country}`)
            .then(res => {
                setAirportDetail(res.data);
                console.log(res.data)
            })
            .catch(err => {
                console.log(err)
            });
    };

    useEffect(() => {
        fetchAirportDetails();
    }, [])

    return (
        <div className="newsInline1">
            <h1 style={{textAlign: "center", color: "orange"}}>AIRPORTS</h1>
            <div style={{display: "flex"}}>
                <Card
                    bg={"dark"}
                    key={"dark"}
                    text={'white'}
                    style={{width: '35rem'}}
                    className="mb-2"
                >
                    <Card.Header style={{textAlign: "center", color: "orange"}}>
                        <table>
                            <tr>
                                <td>
                                    <img
                                        src={plane}
                                        alt="random image"/>
                                </td>
                                <td>
                                    <h1>{airportDetail.name}</h1>
                                </td>
                            </tr>
                        </table>
                    </Card.Header>
                    <Card.Body>
                        <Card.Text>
                            <p>{airportDetail.location} <br/> {airportDetail.state} <br/> {airportDetail.city}
                                <br/> {airportDetail.street} <br/> {airportDetail.street_number} <br/> {airportDetail.phone}</p>
                        </Card.Text>
                        <Button variant={"warning"} href={airportDetail.website}>See more</Button>
                    </Card.Body>
                </Card>
            </div>
        </div>
    );
};

export default AirportDetails;