import React, {useEffect, useState} from 'react';
import "./TouristAttractionsBox.css"
import TouristSingleAttractionCard from "./TouristSingleAttractionCard";
import {Card} from "react-bootstrap";
import axios from "axios";
import countries from "i18n-iso-countries";
import english from "i18n-iso-countries/langs/en.json";

const CARD_HEADER_TITLES = ["Must see", "Your friends will be shocked", "Mom, look...", "Hurry up", "Special place for special you"];


const TouristAttractionsBox = (props) => {
    const city = props.city;
    const country = props.country;
    const countryIsoCode = getCountryIsoCode(country);

    const [attractions, setAttractions] = useState([]);

    const fetchAttractions = () => {
        axios.get(`http://localhost:8080/attractions/${city}/${countryIsoCode}`)
            .then(res => {
                if( res.status === 200 ){
                    setAttractions(res.data);
                }
            })
            .catch(err => {
                console.log(err)
            });
    };

    useEffect(() => {
        fetchAttractions();
    }, [])

    const cardHeaders = randomArrayShuffle(CARD_HEADER_TITLES);

    return (
        <div className="bubble-box">
            <div className="attraction-box">
                <h1 style={{textAlign: "center", color: "orange"}}>ATTRACTIONS</h1>
                {attractions ?
                    <div style={{display: "flex", flexWrap: "wrap"}}>
                        {attractions.map((attractions, index) => {
                            return (
                                <Card
                                    bg={"dark"}
                                    key={"tourist-attractions-box-dark"}
                                    text={'white'}
                                    style={{width: "49%", flex: "50%", borderColor: "orange"}}
                                    className="mb-2"
                                >
                                    <Card.Header style={{textAlign: "center", color: "orange"}}>
                                        <h3 className="h3">{cardHeaders[index]}</h3>
                                    </Card.Header>
                                    <Card.Body>
                                        <TouristSingleAttractionCard key={index} attractions={attractions}/>
                                    </Card.Body>
                                </Card>
                            )
                        })}

                    </div> :
                    <div>Looks like you'll be there first tourist there</div>
                }

            </div>
        </div>
    )

};

export default TouristAttractionsBox;


function randomArrayShuffle(array) {
    let currentIndex = array.length, temporaryValue, randomIndex;
    while (currentIndex !== 0) {
        randomIndex = Math.floor(Math.random() * currentIndex);
        currentIndex -= 1;
        temporaryValue = array[currentIndex];
        array[currentIndex] = array[randomIndex];
        array[randomIndex] = temporaryValue;
    }
    return array;
}

function getCountryIsoCode(countryName) {
    countries.registerLocale(english);
    return countries.getAlpha2Code(countryName, "en");
}