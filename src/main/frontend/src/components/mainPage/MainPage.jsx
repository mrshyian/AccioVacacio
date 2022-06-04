import React, {useEffect, useState} from 'react';
import axios from "axios";
import NewsBox from "./newsBox/NewsBox";
import AirportDetails from "./airportDetails/AirportDetails";
import SearchingPlaceBar from "./searchingPlaceBar/SearchingPlaceBar";
import AddImage from "../userPage/addImage/AddImage";
import WeatherBox from "./weather/WeatherBox";
import LivingCosts from "./livingCosts/LivingCosts";


const MainPage = (props) => {

    const [news, setNews] = useState([]);
    const [IATACode, setIATACode] = useState("WMI");
    const [weather, setWeather] = useState("");
    const [livingCosts, setLivingCosts] = useState([]);

    const fetchLivingCosts = () => {
        console.log(props.itemName)
        axios.get(`http://localhost:8080/living-costs/${props.city}/${props.country}`)
            .then(res =>{setLivingCosts(res.data);})
            .catch(err => {console.log(err)});
    };

    const fetchNewsWorld = () => {
        axios.get(`http://localhost:8080/news/${props.city}`)
            .then(res =>{setNews(res.data);})
            .catch(err => {console.log(err)});
    };

    const fetchIATACode = () => {
        axios.get(`http://localhost:8080/airport/${props.city}`)
            .then(res =>{setIATACode(res.data.airportCode);})
            .catch(err => {console.log(err)});
    };

    const fetchWeather = () => {
        axios.get(`http://localhost:8080/weather/${props.city}`)
            .then(res =>{setWeather(res.data);})
            .catch(err => {console.log(err)});
    };

    useEffect(()=>{
        fetchLivingCosts();
        fetchNewsWorld();
        fetchIATACode();
        fetchWeather();
    }, [])

    return (
        <div>
            <SearchingPlaceBar country={props.country} city={props.city}/>
            <WeatherBox weather={weather}/>
            <NewsBox news={news}/>
            <AirportDetails iata={IATACode}/>
            <LivingCosts livingCosts={livingCosts} />
        </div>
    );
};

export default MainPage;