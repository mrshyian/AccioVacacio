import React, {useEffect, useState} from 'react';
import axios from "axios";
import NewsBox from "./newsBox/NewsBox";
import AirportDetails from "./airportDetails/AirportDetails";


const MainPage = (props) => {

    const [news, setNews] = useState([]);
    const [IATACode, setIATACode] = useState([]);

    const fetchNewsWorld = () => {
        axios.get(`http://localhost:8080/news/${props.city}`)
            .then(res =>{setNews(res.data);})
            .catch(err => {console.log(err)});
    };

    const fetchIATACode = () => {
        axios.get(`http://localhost:8080/airport/${props.city}`)
            .then(res =>{setIATACode(res.data);})
            .catch(err => {console.log(err)});
    };

    useEffect(()=>{
        fetchNewsWorld();
        fetchIATACode();
    }, [])

    return (
        <div>
            <NewsBox news={news}/>
            <div id="select-airport-box">
                {IATACode.map(IATA => {
                    <AirportDetails IATA={IATA}/>
                })}
            </div>
        </div>
    );
};

export default MainPage;