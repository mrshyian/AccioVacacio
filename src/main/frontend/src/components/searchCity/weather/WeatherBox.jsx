import React from 'react';
import "./WeaterBox.css"
import {Card} from 'react-bootstrap';
import temperature1 from "../../../images/temperature1.png"
import temperature2 from "../../../images/temperature2.png"
import pressure from "../../../images/pressure.png"
import humidity from "../../../images/humiditypng.png"
import wing from "../../../images/wind.png"
import clouds from "../../../images/clouds.png"
import MyGoogleMap from "../googleMaps/MyGoogleMap";

const WeatherBox = (props) => {
    return (
        <Card
            bg={"dark"}
            key={"dark"}
            text={'white'}
            style={{ width: '35rem' }}
            className="mb-2"
        >
            <Card.Header style={{textAlign: "center", color: "orange"}}><h2>Weather</h2></Card.Header>
            <Card.Body>
                <Card.Text>
                    <table>
                        <tr>
                            <td>
                                <img className="img" src={temperature1} alt="random image"/>
                            </td>
                            <td>
                                <h2>Temperature: {props.weather.temperature} C°</h2>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <img className="img" src={temperature2} alt="random image"/>
                            </td>
                            <td>
                                <h2>Feels like: {props.weather.feelsLike} C°</h2>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <img className="img" src={pressure} alt="random image"/>
                            </td>
                            <td>
                                <h2>Pressure: {props.weather.pressure} mBar</h2>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <img className="img" src={humidity} alt="random image"/>
                            </td>
                            <td>
                                <h2>Humidity: {props.weather.humidity} g/m³</h2>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <img className="img" src={wing} alt="random image"/>
                            </td>
                            <td>
                                <h2>Wind Speed: {props.weather.wingSpeed} km/h</h2>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <img className="img" src={clouds}/>
                            </td>
                            <td>
                                <h2> {props.weather.description}</h2>
                            </td>
                        </tr>
                    </table>
                </Card.Text>
            </Card.Body>
        </Card>
    );
};

export default WeatherBox;