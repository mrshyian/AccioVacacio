import React, {useEffect, useState} from 'react';
import axios from "axios";
import {Button, Container, Nav, Navbar} from 'react-bootstrap';
import {useNavigate} from "react-router-dom";
import MyGoogleMap from "../googleMaps/MyGoogleMap";

const SearchBox = () => {

    const [data, setData] = useState([]);
    const [selectedCountry, setSelectedCountry] = useState();
    const [getState, setState] = useState([]);
    const [selectedState, setSelectedState] = useState();
    const [cities, setCities] = useState([]);
    const [selectedCity, setSelectedCity] = useState();
    const navigate = useNavigate();
    useEffect(() => {
        axios.get("https://pkgstore.datahub.io/core/world-cities/world-cities_json/data/5b3dd46ad10990bca47b04b4739a02ba/world-cities_json.json")
            .then(res => setData(res.data))
            .catch(err => console.log(err))
    }, [])

    const country = [...new Set(data.map(item => item.country))].sort();

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


    return (
        <div>
            <Navbar variant="dark" bg="dark" expand="lg" style={{marginTop: "0%"}}>
                <Container fluid>
                    <Navbar.Brand href="#home">Select a city:</Navbar.Brand>
                    <Navbar.Toggle aria-controls="navbar-dark-example"/>
                    <Navbar.Collapse id="navbar-dark-example">
                        <Nav>
                            <div className="select">
                                <select onChange={(e) => handleCountry(e)}>
                                    <option value="">Select Country</option>
                                    {country.map(items => <option key={items} value={selectedCountry}>{items}</option>)}
                                </select>
                            </div>
                            <div className="select">
                                <select onChange={(e) => handleState(e)}>
                                    <option value="">Select State</option>
                                    {getState.map(items => <option key={items} value={selectedState}>{items}</option>)}
                                </select>
                            </div>
                            <div className="select">
                                <select onChange={(e) => handleCity(e)}>
                                    <option value="">Select City</option>
                                    {cities.map(items => <option key={items.name}>{items.name}</option>)}
                                </select>
                            </div>
                            <Button
                                variant="outline-warning"
                                style={{marginLeft: '5px'}}
                                onClick={() => navigate("/SearchCity", {
                                    state: {
                                        city: selectedCity,
                                        country: selectedCountry
                                    }
                                })}
                            >
                                Search
                            </Button>
                        </Nav>
                    </Navbar.Collapse>
                </Container>
            </Navbar>

            {/*<MyGoogleMap/>*/}

        </div>
    );
};

export default SearchBox;
