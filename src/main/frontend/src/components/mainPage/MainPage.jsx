import React, {useEffect, useState} from 'react';
import axios from "axios";
import NewsBox from "./newsBox/NewsBox";


const MainPage = (props) => {

    const [news, setNews] = useState([]);

    const fetchNewsWorld = () => {
        axios.get(`http://localhost:8080/news/${props.city}`)
            .then(res =>{setNews(res.data);})
            .catch(err => {console.log(err)});
    };

    useEffect(()=>{
        fetchNewsWorld()
    }, [])

    return (
        <div>
            <NewsBox news={news}/>
        </div>
    );
};

export default MainPage;