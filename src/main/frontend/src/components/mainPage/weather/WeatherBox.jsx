import React from 'react';
import "./WeaterBox.css"

const WeatherBox = (props) => {
    return (
        <div className="weather-box">
            <h1 style={{textAlign: "center", color: "coral"}}>Weather</h1>
            <div className="dupa">
                <div className="back">
                    <table>
                        <tr>
                            <td>
                                <img className="img" src="https://cdn.icon-icons.com/icons2/3104/PNG/512/temperature_thermometer_weather_temperature_hot_sun_icon_191547.png" alt="random image"/>
                            </td>
                            <td>
                                <h2>Temperature: {props.weather.temperature} C°</h2>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <img className="img" src="https://cdn.icon-icons.com/icons2/3000/PNG/512/warm_termometer_weather_hot_temperature_icon_187694.png" alt="random image"/>
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
                                <img className="img" src="https://cdn.icon-icons.com/icons2/527/PNG/512/Humidity_icon-icons.com_52507.png" alt="random image"/>
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
                </div>
            </div>

        </div>
    );
};

export default WeatherBox;