import React, {useEffect, useState} from 'react';
import axios from "axios";
import {Button, Card} from "react-bootstrap";
import plane from "../../../images/plane.png"
import {getCountryIsoCode} from "../touristAttractions/TouristAttractionsBox";

const AirportDetails = (props) => {
    const countryIsoCode = getCountryIsoCode(props.country);
    const [airportDetail, setAirportDetail] = useState([]);

    const fetchAirportDetails = () => {
        axios.get(`http://localhost:8080/airport-info/${props.city}/${countryIsoCode}`)
            .then(res => {
                setAirportDetail(res.data);
            })
            .catch(err => {
                console.log(err)
            });
    };

    useEffect(() => {
        fetchAirportDetails();
    }, [])

    return (
        <div className="newsInline1" >
            <h1 style={{textAlign: "center", color: "orange"}}>AIRPORTS</h1>
            <div style={{display: "flex", justifyContent: "center"}}>
                <Card
                    bg={"dark"}
                    key={"airport-details-dark"}
                    text={'white'}
                    style={{width: '35rem', borderColor: "orange" }}
                    className="mb-2"
                >
                    <Card.Header style={{textAlign: "center", color: "orange"}}>
                        { airportDetail ?
                            <table>
                                <tr>
                                    <td>
                                        <img
                                            src={plane}
                                            alt="random image"/>
                                    </td>
                                    <td>
                                        <h1>{airportDetail.name}</h1>
                                        <h1>{airportDetail.iataCode} ({airportDetail.icaoCode})</h1>
                                    </td>
                                </tr>
                            </table> :
                            <table>
                                <tr>
                                    <td>
                                        <img
                                            src={plane}
                                            alt="random image"/>
                                    </td>
                                    <td>
                                        <h1>Upss...</h1>
                                        <h1>There are no airports here</h1>
                                    </td>
                                </tr>
                            </table>
                        }
                    </Card.Header>
                    <Card.Body>
                        <Card.Text>
                            { airportDetail ?
                                    <div>
                                        <br/> <div>{airportDetail.address}
                                        <br/> {airportDetail.phone}</div>
                                    </div>
                                    :
                                    <div><br/></div>
                            }
                        </Card.Text>
                        {
                            airportDetail ?
                                <Button variant={"warning"} href={airportDetail.website}>See more</Button> :
                                <div></div>
                        }
                    </Card.Body>
                </Card>
            </div>
        </div>
    );
};

export default AirportDetails;