import React, {useEffect, useState} from 'react';
import axios from "axios";
import NewsBox from "./newsBox/NewsBox";
import AirportDetails from "./airportDetails/AirportDetails";
import SearchingPlaceBar from "./searchingPlaceBar/SearchingPlaceBar";
import WeatherBox from "./weather/WeatherBox";
import EmergencyNumbers from "./emergencyNumbers/EmergencyNumbers";
import LivingCoasts from "./livingCosts/LivingCoasts";
import CrimeRating from "./crimaRating/CrimeRating";
import TouristAttractionsBox from "./touristAttractions/TouristAttractionsBox";
import {useLocation} from "react-router-dom";
import Booking from "./booking/Booking";
import MyGoogleMap from "./googleMaps/MyGoogleMap";
import {Card} from "react-bootstrap";


const SearchCity = () => {
    const [news, setNews] = useState([]);
    const [weather, setWeather] = useState("");
    const [emergencyNumber, setEmergencyNumber] = useState("");
    const [livingCosts, setLivingCosts] = useState([]);
    const [crimeRating, setCrimeRating] = useState([]);

    const location = useLocation()
    const country = location.state.country;
    const city = location.state.city;

    const [longitude, setLongitude] = useState(21.0118);
    const [latitude, setLatitude] = useState(52.2298);


    const getCoordinates = () => {
        axios.get(`http://localhost:8080/get_coordinates/${city}/${country}`)
            .then(res => {
                setLongitude(res.data.lon);
                setLatitude(res.data.lat);
            })
            .catch(err => {
                console.log(err)
            });
    }

    const fetchLivingCosts = () => {
        axios.get(`http://localhost:8080/living-costs/${city}/${country}`)
            .then(res => {
                setLivingCosts(res.data);
                console.log(res.data)
            })
            .catch(err => {
                console.log(err)
            });
    };

    const fetchNewsWorld = () => {
        axios.get(`http://localhost:8080/news/${city}/${country}`)
            .then(res => {
                setNews(res.data);
                console.log(res.data)
            })
            .catch(err => {
                console.log(err)
            });
    };

    const fetchWeather = () => {
        axios.get(`http://localhost:8080/weather/${city}/${country}`)
            .then(res => {
                setWeather(res.data);
            })
            .catch(err => {
                console.log(err)
            });
    };

    const fetchEmergencyNumbers = () => {
        axios.get(`http://localhost:8080/emergency_numbers/${city}/${country}`)
            .then(res => {
                setEmergencyNumber(res.data);
            })
            .catch(err => {
                console.log(err)
            });
    };

    const fetchCrimeRating = () => {
        axios.get(`http://localhost:8080/crime_rating/${city}/${country}`)
            .then(res => {
                setCrimeRating(res.data);
            })
            .catch(err => {
                console.log(err)
            });
    };

    useEffect(() => {
        fetchNewsWorld();
        fetchWeather();
        fetchLivingCosts();
        fetchEmergencyNumbers();
        fetchCrimeRating();
        getCoordinates();
    }, [])



    return (
        <div className="bubble-box">
        <Card
            bg={"dark"}
            key={"search-city-dark"}
            text={'white'}
            className="mb-2"
        >
            <SearchingPlaceBar country={country} city={city}/>
            <div className="weather-box">
                <WeatherBox weather={weather}/>
                <CrimeRating crimeRating={crimeRating} city={city}/>
                <EmergencyNumbers emergencyNumber={emergencyNumber}/>
            </div>
            <TouristAttractionsBox country={country} city={city}/>
            <NewsBox news={news}/>
            <AirportDetails country={country} city={city}/>
            <LivingCoasts livingCosts={livingCosts}/>
            <div className="weather-box" style={{display: "flex", justifyContent: "center"}}>
                <Booking country={country} city={city}/>
                <MyGoogleMap longitude={longitude} latitude={latitude}/>
            </div>

        </Card>
        </div>
    );
};

export default SearchCity;
