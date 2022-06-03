import React from 'react';
import "./NewsBox.css"
import SingleNews from "./SingleNews";


const NewsBox = (props) => {
    return (<div className="newsInline">
            <h1 style={{textAlign: "center", color: "coral"}}>NEWS</h1>
        {props.news.map((news, index) => {
            return (
            <SingleNews key={index} news={news}/>
            )
        })}
        </div>
    )

};

export default NewsBox;