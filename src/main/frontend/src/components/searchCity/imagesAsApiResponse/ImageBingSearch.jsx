import React from 'react';
import {Button} from "react-bootstrap";
import "./ImageBingSearch.css"

const ImageBingSearch = (props) => {
    const images = props.attractions.imageUrl;
    const websiteUrl = props.attractions.website;
    const imagesList = images[Object.keys(images)[0]];
    const imageIndex = Math.floor(Math.random() * imagesList.length);
    const imageUrl = imagesList[imageIndex];


    return (
        <div  style={{flexDirection: "column", display: "inline", buttom: "2px"}}>
            <img className="img-bing-search" src={imageUrl}/>
            <Button variant={"warning"} style={{position: "fixed", right: "50px", top: "210px"}}>
                <a href={websiteUrl} style={{color: "black"}}>
                    <strong>Read more</strong>
                </a>
            </Button>
        </div>
    );
};

export default ImageBingSearch;