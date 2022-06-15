import React from 'react';
import ImageBingSearch from "../../ImageBingSearch";

const TouristSingleAttractionCard = (props) => {

    const locationName = props.attractions.locationName;
    const locationAddress = props.attractions.locationAddress;
    const distanceToLocation = props.attractions.distanceToLocation;
    const website = props.attractions.website;

    return (
        <div className="wrapper">
            <div className="blog_post">
                <div className="img_pod">
                    <img src="https://cdn.icon-icons.com/icons2/203/PNG/128/diagram-01_24516.png" alt="random image"/>
                </div>
                <div className="minus-margin">
                    <h1>{locationName}</h1>
                    <p>{locationAddress}</p>
                    <p>{distanceToLocation}</p>
                    <ImageBingSearch key={props.index} attractions={props.attractions}/>
                    <a className="btn_primary" href={website}>Read About...</a>
                </div>
            </div>
        </div>
    );
};

export default TouristSingleAttractionCard;