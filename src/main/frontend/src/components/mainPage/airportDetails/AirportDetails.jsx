import React, {useEffect, useState} from 'react';
import axios from "axios";
import "./AirportDetails.css"

const AirportDetails = (props) => {

    const [airportDetail, setAirportDetail] = useState([]);

    const fetchAirportDetails = () => {
        console.log(props.iata)
        axios.get(`http://localhost:8080/airport-details/${props.iata}`)
            .then(res =>{setAirportDetail(res.data);
                console.log(res.data)})
            .catch(err => {console.log(err)});
    };

    useEffect(()=>{
        fetchAirportDetails();
    }, [])

    return (
        <div className="newsInline1">
            <h1 style={{textAlign: "center", color: "coral"}}>AIRPORTS</h1>
            <div className="wrapper">
                <div className="blog_post">
                    <div className="img_pod">
                        <img src="https://cdn.icon-icons.com/icons2/2393/PNG/512/travel_transmit_route_tourism_flight_icon_145634.png" alt="random image"/>
                    </div>
                    <div className="minus-margin">
                    <span className="container_copy">
                        <h1>{airportDetail.name}</h1>
                        <a className="btn_primary" href={airportDetail.website}>See More</a>
                    </span>
                        <p>{airportDetail.location} <br/> {airportDetail.state} <br/> {airportDetail.city} <br/> {airportDetail.street} <br/> {airportDetail.street_number} <br/> {airportDetail.phone}</p>
                    </div>
                </div>
            </div>
        </div>

    );
};

export default AirportDetails;