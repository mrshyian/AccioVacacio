import React from 'react';
import {Button} from "react-bootstrap";

const ImageBingSearch = (props) => {
    const images = props.attractions.imageUrl;
    const imagesList = images[Object.keys(images)[0]];
    const imageIndex = Math.floor(Math.random() * imagesList.length);
    const imageUrl = imagesList[imageIndex];

    return (
        <div style={{flexDirection: "column", display: "inline", buttom: "2px"}}>
            <img src={imageUrl}/>
            <Button variant={"warning"} style={{position: "fixed", right: "50px"}}>
                <a href={props.website} style={{color: "black"}}>
                    <strong>Read more</strong>
                </a>
            </Button>
        </div>
    );
};

export default ImageBingSearch;