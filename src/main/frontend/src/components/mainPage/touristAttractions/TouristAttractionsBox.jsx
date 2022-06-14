import React from 'react';
import "./TouristAttractionsBox.css"
import TouristSingleAttractionCard from "./TouristSingleAttractionCard";


const TouristAttractionsBox = (props) => {
    return (<div className="newsInline">
            <h1 style={{textAlign: "center", color: "coral"}}>Attractions</h1>
                {props.attractions.map((attractions, index) => {
                    return (
                    <TouristSingleAttractionCard key={index} attractions={attractions}/>
                    )
                })}
        </div>
    )

};

export default TouristAttractionsBox;