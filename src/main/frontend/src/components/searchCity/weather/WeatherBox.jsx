import React from 'react';
import "./WeaterBox.css"
import {Card} from 'react-bootstrap';

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
                                <img className="img" src="https://cdn.icon-icons.com/icons2/2745/PNG/512/temperature_icon_175973.png" alt="random image"/>
                            </td>
                            <td>
                                <h2>Temperature: {props.weather.temperature} C°</h2>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <img className="img" src="https://cdn.icon-icons.com/icons2/2164/PNG/512/temperature_temperature_hot_forecast_weather_icon_133098.png" alt="random image"/>
                            </td>
                            <td>
                                <h2>Feels like: {props.weather.feelsLike} C°</h2>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <img className="img" src="https://cdn.icon-icons.com/icons2/38/PNG/512/pressure_4667.png" alt="random image"/>
                            </td>
                            <td>
                                <h2>Pressure: {props.weather.pressure} mBar</h2>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <img className="img" src="https://cdn.icon-icons.com/icons2/94/PNG/512/humidity_wather_16790.png" alt="random image"/>
                            </td>
                            <td>
                                <h2>Humidity: {props.weather.humidity} g/m³</h2>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <img className="img" src="https://cdn.icon-icons.com/icons2/8/PNG/128/weather_wind_flag_storm_travel_1455.png" alt="random image"/>
                            </td>
                            <td>
                                <h2>Wind Speed: {props.weather.wingSpeed} km/h</h2>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <img className="img" src="https://cdn.icon-icons.com/icons2/8/PNG/256/cloudyweather_cloud_inpart_day_wind_thunder_sunny_rain_darkness_nublad_1459.png" alt="random image"/>
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