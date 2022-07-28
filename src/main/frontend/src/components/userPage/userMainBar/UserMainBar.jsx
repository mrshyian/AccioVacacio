import React, {useEffect, useState} from 'react';
import InformationAboutUser from "./userMainBarComponents/informationAboutUser/InformationAboutUser";
import CountryCounter from "./userMainBarComponents/countryCounter/CountryCounter";
import {Card} from "react-bootstrap";
import "./UserMainBar.css"
import axios from "axios";

const UserMainBar = () => {

    const [myUser, setMyUser] = useState([])

    const getUserFromDB = () => {
        axios.get(`http://localhost:8080/usermainbar`, { headers: {"Authorization" : `Bearer ${sessionStorage.getItem("token")}`}})
            .then(res => {
                console.log(res.data)
                setMyUser(res.data);
            })
            .catch(err => {
                console.log(err)
            });
    };

    useEffect(() => {
            getUserFromDB();
    }, [])

    return (
        <Card
            bg={"dark"}
            key={"dark"}
            text={'white'}
            className="mb-2 bg-opacity"
        >
            <Card.Body>
                <Card.Text style={{paddingLeft: "5%", paddingRight: "5%"}}>
                    <InformationAboutUser myUser={myUser}/>
                </Card.Text>
            </Card.Body>
        </Card>
    );
};

export default UserMainBar;