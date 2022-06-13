import React from 'react';

const SingleNews = (props) => {

    let currentMonth = new Date().toLocaleString("en-US", {month: "long"});
    let currentDay = new Date().getUTCDate();
    let currentYear = new Date().getFullYear()

    return (
        <div className="wrapper">
            <div className="blog_post">
                <div className="img_pod">
                    <img src="https://cdn.icon-icons.com/icons2/203/PNG/128/diagram-01_24516.png" alt="random image"/>
                </div>
                <div className="minus-margin">
                    <span className="container_copy">
                        <h3 style={{marginLeft: "30px"}}>{currentDay} {currentMonth} {currentYear}</h3>
                        <a className="btn_primary" href={props.news.link}>Read More</a>
                    </span>
                    <h1>{props.news.title}</h1>
                    <p>{props.news.summary}</p>
                </div>
            </div>
        </div>
    );
};

export default SingleNews;