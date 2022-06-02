import React, {useEffect, useState} from 'react';
import axios from "axios";
import "./SearchBox.css"
import ReactDOM from "react-dom/client";
import App from "../../App";
import Header from "../header/Header";
import MainPage from "../mainPage/MainPage";

const SearchBox = () => {

    const [data, setData] = useState([]);
    const [selectedCountry, setSelectedCountry] = useState();
    const [getState, setState] = useState([]);
    const [selectedState, setSelectedState] = useState();
    const [cities, setCities] = useState([]);
    const [selectedCity, setSelectedCity] = useState();

    useEffect(() => {
        axios.get("https://pkgstore.datahub.io/core/world-cities/world-cities_json/data/5b3dd46ad10990bca47b04b4739a02ba/world-cities_json.json")
            .then(res => setData(res.data))
            .catch(err => console.log(err))
    }, [])

    const country = [...new Set(data.map(item=> item.country))].sort();

    const handleCountry = (e) => {
        setSelectedCountry(e.target.value)
        let states = data.filter(state => state.country === e.target.value);
        states = [...new Set(states.map(item => item.subcountry))].sort();
        setState(states);
    }

    const handleState = (e) => {
        setSelectedState(e.target.value)
        let cities = data.filter(city => city.subcountry === e.target.value);
        setCities(cities);
    }

    const handleCity = (e) => {
        setSelectedCity(e.target.value)
    }

    const root = ReactDOM.createRoot(document.getElementById('root'));


    const loadSearchingPage = () => {
        root.render(
            <React.StrictMode>
                <Header inSession={false}/>
                <MainPage country={selectedCountry} city={selectedCity}/>
            </React.StrictMode>
        );
    }
    console.log(selectedCountry, selectedState, selectedCity)

    return (
        <div className="search-box">
            <div className="select">
                <select onChange={(e) => handleCountry(e)}>
                    <option value="">Select Country</option>
                    {country.map(items=> <option key={items} value={selectedCountry}>{items}</option> )}
                </select>
            </div>
            <div className="select">
                <select onChange={(e) => handleState(e)}>
                    <option value="">Select State</option>
                    {getState.map(items=> <option key={items} value={selectedState}>{items}</option> )}
                </select>
            </div>
            <div className="select">
                <select onChange={(e) => handleCity(e)}>
                    <option value="">Select City</option>
                    {cities.map(items=> <option key={items.name}>{items.name}</option> )}
                </select>
            </div>
            <button onClick={loadSearchingPage}>Search</button>
        </div>
    );
};

export default SearchBox;