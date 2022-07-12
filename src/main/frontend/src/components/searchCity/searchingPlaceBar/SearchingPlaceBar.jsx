import React from 'react';
import {Navbar} from "react-bootstrap";

const SearchingPlaceBar = (props) => {
    return (
        <Navbar variant={"light"} bg={"dark"} style={{border: "3px solid orange"}}>
            <div style={{marginLeft: "auto", marginRight: "auto"}}>
                <Navbar.Brand style={{marginLeft: "40%", color: "orange"}}><h1>{props.country} / {props.city}</h1></Navbar.Brand>
            </div>
        </Navbar>
    );
};

export default SearchingPlaceBar;