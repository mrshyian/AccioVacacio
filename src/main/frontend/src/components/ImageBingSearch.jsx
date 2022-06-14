import React from 'react';

const ImageBingSearch = (props) => {
    const images = props.attractions.imageUrl;
    const imagesList = images[Object.keys(images)[0]];
    const imageIndex = Math.floor(Math.random() * imagesList.length);
    const imageUrl = imagesList[imageIndex];

    return (
        <div>
            <img src={imageUrl}/>
        </div>
    );
};

export default ImageBingSearch;