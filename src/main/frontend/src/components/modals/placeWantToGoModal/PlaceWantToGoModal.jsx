import {Button, Modal, Form} from "react-bootstrap";
import axios from "axios";
import React, {useEffect, useState} from "react";


const PlaceWantToGoModal = (props) => {

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


    useEffect(()=>{
        setShowLoginModal(props.visible)
    }, [])

    const [showLoginModal, setShowLoginModal] = useState(false);
    const handleCloseLoginModal = () => setShowLoginModal(false);

    const sendDataToServer = () => {
        const url = "http://localhost:8080/placewanttogo";
        axios.post(url,{
            country: selectedCountry,
            city: selectedCity
        })
            .then(res=>{
                console.log(res);
                window.location.reload();
            })
        handleCloseLoginModal()
    }


    return (
        <Modal show={showLoginModal} onHide={handleCloseLoginModal} style={{background: "rgba(0, 0, 0, 0.6)", color: "orange"}}>
            <Modal.Header closeButton style={{background: "rgb(40,40,40)"}}>
                <Modal.Title>New place</Modal.Title>
            </Modal.Header>
            <Modal.Body style={{background: "rgb(20,20,20)"}}>
                <Form style={{background: "rgb(20,20,20)"}}>
                    <Form.Group className="mb-3">
                        <select onChange={(e) => handleCountry(e)}>
                            <option value="">Select Country</option>
                            {country.map(items => <option key={items} value={selectedCountry}>{items}</option>)}
                        </select>
                    </Form.Group>
                    <Form.Group
                        className="mb-3"
                    >
                        <select onChange={(e) => handleState(e)}>
                            <option value="">Select State</option>
                            {getState.map(items => <option key={items} value={selectedState}>{items}</option>)}
                        </select>
                    </Form.Group>
                    <Form.Group className="mb-3">
                        <select onChange={(e) => handleCity(e)}>
                            <option value="">Select City</option>
                            {cities.map(items => <option key={items.name}>{items.name}</option>)}
                        </select>
                    </Form.Group>
                </Form>
            </Modal.Body>
            <Modal.Footer  style={{background: "rgb(40,40,40)"}}>
                <Button variant="outline-secondary" onClick={handleCloseLoginModal}>
                    Close
                </Button>
                <Button variant="outline-warning" onClick={sendDataToServer}>
                    Create
                </Button>
            </Modal.Footer>
        </Modal>
    );
};

export default PlaceWantToGoModal;