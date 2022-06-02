import React from 'react';
import "./NewsBox.css"
import SingleNews from "./SingleNews";


const NewsBox = (props) => {
    return (<div className="newsInline">
        {props.news.map(news => {
            return (
            <SingleNews news={news}/>
            )
        })}
        </div>
    )

};

export default NewsBox;