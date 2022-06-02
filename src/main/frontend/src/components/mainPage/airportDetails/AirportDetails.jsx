import React from 'react';
import {useEffect, useState} from "@types/react";
import axios from "axios";

const AirportDetails = (props) => {

    const [airportDetail, setAirportDetail] = useState([]);

    const fetchAirportDetails = () => {
        axios.get(`http://localhost:8080/airport-details/${props.IATA}`)
            .then(res =>{setAirportDetail(res.data);})
            .catch(err => {console.log(err)});
    };

    useEffect(()=>{
        fetchAirportDetails();
    }, [])

    return (
        <div className="wrapper">
            <div className="blog_post">
                <div className="img_pod">
                    <img src="https://cdn.icon-icons.com/icons2/2393/PNG/512/travel_transmit_route_tourism_flight_icon_145634.png" alt="random image"/>
                </div>
                <div className="minus-margin">
                    <span className="container_copy">
                        <a className="btn_primary" href={airportDetail.website}>See More</a>
                    </span>
                    <h1>{airportDetail.name}</h1>
                    <p>{airportDetail.location}</p>
                    <p>{airportDetail.state}</p>
                    <p>{airportDetail.city}</p>
                    <p>{airportDetail.street}</p>
                    <p>{airportDetail.street_number}</p>
                    <p>{airportDetail.phone}</p>
                </div>
            </div>
        </div>
    );
};

export default AirportDetails;