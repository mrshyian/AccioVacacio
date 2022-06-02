import React, {useEffect, useState} from 'react';
import axios from "axios";
import {logDOM} from "@testing-library/react";


const MainPage = (props) => {

    const [news, setNews] = useState([]);

    const fetchNewsWorld = () => {
        axios.get(`http://localhost:8080/news/${props.city}`)
            .then(res =>{console.log(res.data);})
            .catch(err => {console.log(err)});
        console.log(news)
    };

    useEffect(()=>{
        fetchNewsWorld()
    })

    return (
        <div>
            {/*<NewsBox news={news}/>*/}
        </div>
    );
};

export default MainPage;