import React from 'react';
import oneStar from './icon/1star.png'
import twoStar from './icon/2stars.png'
import threeStar from './icon/3stars.png'
import fourStar from './icon/4stars.png'
import fiveStar from './icon/5stars.png'
import safely from './icon/safely.png'
import "./CrimeRating.css"


const CrimeRating = (props) => {
    let img;
    let returned=1;
    props.crimeRating.map((rating, index) => {
        if (props.city === props.crimeRating[index].city) {
            if (props.crimeRating[index].index < 21) {
                img = fiveStar;
            } else if (props.crimeRating[index].index < 41) {
                img = fourStar;
            } else if (props.crimeRating[index].index < 61) {
                img = threeStar;
            } else if (props.crimeRating[index].index < 81) {
                img = twoStar;
            } else {
                img = oneStar;
            }
            returned =
                <div className="dupa-for-crime-rating">
                    <div className="back-for-crime-rating">
                        <h1 style={{textAlign: "center", color: "coral"}}>Crime Rating</h1>
                        <img className="img-for-crime-rating"
                             src={img}
                             alt="random image"/>
                    </div>
                </div>
        }
    })

    if (returned !== 1) {
        return (
            returned
        );
    } else {
        return (
            <div className="dupa-for-crime-rating">
                <div className="back-for-crime-rating">
                    <h1 style={{textAlign: "center", color: "coral"}}>Crime Rating</h1>
                    <img className="img-for-crime-rating"
                         src={safely}
                         alt="random image"/>
                </div>
            </div>
        );
    }

}

export default CrimeRating;