import React, {useEffect} from 'react';
import NewsBox from "./newsBox/NewsBox";
import axios from "axios";
import {useState} from "@types/react";

const [news, setNews] = useState([]);

// // PRZYKŁAD! ---------------------------------------------------------
// const news = [
//     {
//         "NewsTitle": "Pierwszy tytul",
//         "NewsText": "Pierwsza wiadomość",
//         "NewsLink": "https://www.youtube.com/watch?v=3rzgrP7VA_Q"
//     },
//     {
//         "NewsTitle": "Drugi tytul",
//         "NewsText": "Druga wiadomość",
//         "NewsLink": "https://www.youtube.com/watch?v=3rzgrP7VA_Q"
//     },
//     {
//         "NewsTitle": "Trzeci tytul",
//         "NewsText": "Trzecia wiadomość",
//         "NewsLink": "https://www.youtube.com/watch?v=3rzgrP7VA_Q"
//     }
// ]
// // PRZYKŁAD! ---------------------------------------------------------

const MainPage = () => {

    const fetchNewsWorld = ({cityName}) => {
        axios.get(`http://localhost:8080/weather/${cityName}`).then(res =>{
            setNews(res.data);
        });
    };

    useEffect(()=>{
        fetchNewsWorld("Warsaw")
    })

    return (
        <div>
            <NewsBox news={news}/>
        </div>
    );
};

export default MainPage;