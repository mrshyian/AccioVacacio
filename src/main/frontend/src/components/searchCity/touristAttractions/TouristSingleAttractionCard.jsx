import React from 'react';
import ImageBingSearch from "../imagesAsApiResponse/ImageBingSearch";

const TouristSingleAttractionCard = (props) => {

    const locationName = props.attractions.locationName;
    const locationAddress = props.attractions.locationAddress;
    const distanceToLocation = props.attractions.distanceToLocation;
    const website = props.attractions.website;

    return (
        <div className="wrapper">
            <div className="blog_post">
                <div className="img_pod">
                    <img src="https://img.icons8.com/external-smashingstocks-glyph-smashing-stocks/344/external-map-pointer-medical-smashingstocks-glyph-smashing-stocks.png" alt="random image"
                    style={{maxWidth: "60px", maxHeight:"60px"}}/>
                </div>
                <div className="minus-margin" style={{width: "49%", flex: "50%"}}>
                    <h1>{locationName}</h1>
                    <p>Address: <em>{locationAddress}</em></p>
                    <p>Only in <strong>{distanceToLocation}</strong> meters from center</p>
                </div>
                <ImageBingSearch key={props.index} attractions={props.attractions} website={{website}}/>
            </div>
        </div>
    );
};

export default TouristSingleAttractionCard;